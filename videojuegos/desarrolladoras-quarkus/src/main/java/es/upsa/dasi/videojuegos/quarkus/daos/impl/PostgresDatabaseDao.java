package es.upsa.dasi.videojuegos.quarkus.daos.impl;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Desarrolladora;
import es.upsa.dasi.videojuegos.quarkus.daos.DatabaseDao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PostgresDatabaseDao implements DatabaseDao {

    @Inject
    DataSource dataSource;
    @Override
    public List<Desarrolladora> selectDesarrolladoras() throws VideojuegoException {

        List<Desarrolladora> desarrolladoras = new ArrayList<>();
        String SQL = """
                     SELECT d.id, d.nombre, d.nacionalidad, d.fecha_creacion, d.foto
                       FROM desarrolladoras d
                     """;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL)
        )
        {
            while (resultSet.next() )
            {
                desarrolladoras.add( Desarrolladora.builder()
                        .withId( resultSet.getString(1) )
                        .withNombre( resultSet.getString(2) )
                        .withNacionalidad( resultSet.getString(3) )
                        .withFecha_creacion( resultSet.getDate(4) )
                        .withFoto( resultSet.getString(5) )
                        .build()
                );
            }

        } catch (SQLException sqlException)
        {
            throw new VideojuegoException(sqlException);
        }

        return desarrolladoras;
    }

    @Override
    public Optional<Desarrolladora> selectDesarrolladoraById(String id) throws VideojuegoException {

        final String SQL = """
                           SELECT d.id, d.nombre, d.nacionalidad, d.fecha_creacion, d.foto
                            FROM desarrolladoras d
                            WHERE d.id = ?
                           """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)
        )
        {
            preparedStatement.setString(1, id);
            try ( ResultSet resultSet = preparedStatement.executeQuery() )
            {
                if (resultSet.next() == false ) return Optional.empty();
                return Optional.of( Desarrolladora.builder()
                        .withId( resultSet.getString(1) )
                        .withNombre( resultSet.getString(2) )
                        .withNacionalidad( resultSet.getString(3) )
                        .withFecha_creacion( resultSet.getDate(4) )
                        .withFoto( resultSet.getString(5) )
                        .build()
                );
            }
        } catch (SQLException sqlException)
        {
            throw new VideojuegoException(sqlException);
        }
    }
}
