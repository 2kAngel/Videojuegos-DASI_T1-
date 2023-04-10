package es.upsa.dasi.videojuegos.quarkus.daos.impl;

import es.upsa.dasi.videojuegos.exceptions.DesarrolladoraNotFoundException;
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
                        .withFecha_creacion(resultSet.getDate(4).toLocalDate())
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
                        .withFecha_creacion(resultSet.getDate(4).toLocalDate())
                        .withFoto( resultSet.getString(5) )
                        .build()
                );
            }
        } catch (SQLException sqlException)
        {
            throw new VideojuegoException(sqlException);
        }
    }

    @Override
    public void updateDesarrolladora(Desarrolladora desarrolladora) throws VideojuegoException {

        final String SQL = """
                           UPDATE desarrolladoras
                              SET nombre = ?, nacionalidad = ?, fecha_creacion = ?, foto = ?
                            WHERE id = ?   
                           """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)
        )
        {
            preparedStatement.setString(1, desarrolladora.nombre());
            preparedStatement.setString(2, desarrolladora.nacionalidad());
            preparedStatement.setDate(3, Date.valueOf(desarrolladora.fecha_creacion()));
            preparedStatement.setString(4, desarrolladora.foto());
            preparedStatement.setString(5, desarrolladora.id());

            int count = preparedStatement.executeUpdate();
            if ( count == 0 ) throw new DesarrolladoraNotFoundException(desarrolladora.id());

        } catch (SQLException sqlException)
        {
            throw new VideojuegoException(sqlException);

        }
    }

    @Override
    public void deleteDesarrolladoraById(String id) throws VideojuegoException {

        final String SQL = """
                           DELETE 
                             FROM desarrolladoras
                            WHERE id = ?
                           """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)
        )
        {
            preparedStatement.setString(1, id);

            int count = preparedStatement.executeUpdate();
            if ( count == 0 ) throw new DesarrolladoraNotFoundException(id);

        } catch (SQLException sqlException)
        {
            throw new VideojuegoException(sqlException);

        }
    }

    @Override
    public Desarrolladora insertDesarrolladora(Desarrolladora desarrolladora) throws VideojuegoException {

        final String SQL = """
                            INSERT INTO desarrolladoras(id, nombre, nacionalidad, fecha_creacion, foto)
                                          VALUES(nextval('seq_desarrolladoras'), ?, ?, ?, ?)
                           """;
        String[] columns = { "id" };

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL, columns)
        )
        {
            preparedStatement.setString(1, desarrolladora.nombre());
            preparedStatement.setString(2, desarrolladora.nacionalidad());
            preparedStatement.setDate(3, Date.valueOf(desarrolladora.fecha_creacion()));
            preparedStatement.setString(4, desarrolladora.foto());
            preparedStatement.executeUpdate();
            try (ResultSet rs = preparedStatement.getGeneratedKeys() )
            {
                rs.next();
                return desarrolladora.withId( rs.getString(1) );
            }

        } catch (SQLException sqlException)
        {
            throw new VideojuegoException(sqlException);
        }
    }
}
