package es.upsa.dasi.daos.impl;

import es.upsa.dasi.daos.DatabaseDao;
import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Videojuego;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.*;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PostgresDatabaseDao implements DatabaseDao {

    @Inject
    DataSource dataSource;

    @Override
    public List<Videojuego> selectVideojuegos() throws VideojuegoException {

        List<Videojuego> videojuegos = new ArrayList<>();

        String SQL = """
                     SELECT v.id, v.nombre, v.fecha_lanzamiento, v.genero, v.cartel
                       FROM videojuegos v
                     """;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL))
        {

            while (resultSet.next() )
            {
                videojuegos.add( Videojuego.builder()
                        .withId( resultSet.getString(1) )
                        .withNombre( resultSet.getString(2) )
                        .withFecha_lanzamiento( resultSet.getDate(3) )
                        .withGenero( resultSet.getString(4) )
                        .withCartel( resultSet.getString(5) )
                        .build()
                );
            }

        } catch (SQLException sqlException)
        {

            throw new VideojuegoException(sqlException);
        }
        return videojuegos;
    }

    @Override
    public Optional<Videojuego> selectVideojuegoById(String id) throws VideojuegoException {

        final String SQL = """
                           SELECT v.id, v.titulo, v.estreno, v.rating, v.sinopsis, v.cartel
                             FROM videojuegos v
                            WHERE v.id = ?
                           """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL))
        {

            preparedStatement.setString(1, id);

            try ( ResultSet resultSet = preparedStatement.executeQuery() )
            {
                if (resultSet.next() == false ) return Optional.empty();
                return Optional.of( Videojuego.builder()
                        .withId( resultSet.getString(1) )
                        .withNombre( resultSet.getString(2) )
                        .withFecha_lanzamiento( resultSet.getDate(3) )
                        .withGenero( resultSet.getString(4) )
                        .withCartel( resultSet.getString(5) )
                        .build()
                );
            }
        } catch (SQLException sqlException)
        {

            throw new VideojuegoException(sqlException);
        }
    }
}
