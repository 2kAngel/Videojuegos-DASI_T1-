package es.upsa.dasi.daos.impl;

import es.upsa.dasi.daos.DatabaseDao;
import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.exceptions.VideojuegoNotFoundException;
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
                        .withFecha_lanzamiento(resultSet.getDate(3).toLocalDate())
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
                           SELECT v.id, v.nombre, v.fecha_lanzamiento, v.genero, v.cartel
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
                        .withFecha_lanzamiento(resultSet.getDate(3).toLocalDate())
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

    @Override
    public void updateVideojuego(Videojuego videojuego) throws VideojuegoException {
        final String SQL = """
                           UPDATE videojuegos
                              SET id = ?, nombre = ?, fecha_lanzamiento = ?, genero = ?, cartel = ?
                            WHERE id = ?   
                           """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)
        )
        {
            preparedStatement.setString(1, videojuego.getId());
            preparedStatement.setString(6, videojuego.getId());
            preparedStatement.setString(2, videojuego.getNombre());
            preparedStatement.setDate(3, Date.valueOf(videojuego.getFecha_lanzamiento()));
            preparedStatement.setString(4, videojuego.getGenero());
            preparedStatement.setString(5, videojuego.getCartel());

            int count = preparedStatement.executeUpdate();
            if ( count == 0 ) throw new VideojuegoNotFoundException(videojuego.getId());

        } catch (SQLException sqlException)
        {
            throw new VideojuegoException(sqlException);

        }
        //da
    }

    @Override
    public void deleteVideojuegoById(String id) throws VideojuegoException {
        final String SQL = """
                           DELETE 
                             FROM videojuegos
                            WHERE id = ?
                           """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)
        )
        {
            preparedStatement.setString(1, id);

            int count = preparedStatement.executeUpdate();
            if ( count == 0 ) throw new VideojuegoNotFoundException(id);

        } catch (SQLException sqlException)
        {
            throw new VideojuegoException(sqlException);

        }
    }

    @Override
    public Videojuego insertVideojuego(Videojuego videojuego) throws VideojuegoException {
        final String SQL = """
                            INSERT INTO videojuegos(id, nombre, fecha_lanzamiento, genero, cartel)
                                   VALUES(nextval('seq_videojuegos'), ?, ?, ?, ?)
                           """;
        String[] columns = { "id" };

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL, columns)
        )
        {
            //preparedStatement.setString(1, videojuego.id()); -> como se autogenera aqui ya no vale
            preparedStatement.setString(1, videojuego.getNombre());
            preparedStatement.setDate(  2, Date.valueOf(videojuego.getFecha_lanzamiento()));
            preparedStatement.setString(3, videojuego.getGenero());
            preparedStatement.setString(4, videojuego.getCartel());
            preparedStatement.executeUpdate();
            try (ResultSet rs = preparedStatement.getGeneratedKeys() )
            {
                rs.next();
                return videojuego.withId( rs.getString(1) );
            }

        } catch (SQLException sqlException)
        {
            throw new VideojuegoException(sqlException);
        }
    }
}
