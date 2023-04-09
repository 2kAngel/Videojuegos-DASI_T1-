package es.upsa.dasi.videojuegos.plataformas.quarkus.daos.impl;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.mappers.Mappers;
import es.upsa.dasi.videojuegos.model.Plataformas;
import es.upsa.dasi.videojuegos.model.Videojuego;
import es.upsa.dasi.videojuegos.plataformas.quarkus.daos.DatabaseDao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PostgresDatabaseDao implements DatabaseDao {
    @Inject
    DataSource dataSource;

    @Override
    public Plataformas selectPlataformasPelicula(String id) throws VideojuegoException {

        List<Plataformas> plataformas = new ArrayList<>();

        final String SQL = """
                           SELECT p.id,p.id_videojuego, p.id_desarrolladora, p.nombre, p.fecha_lanzamiento, p.foto
                             FROM plataformas p
                            WHERE p.id_videojuego = ? 
                           """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement( SQL )
        )
        {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery())
            {
                while (resultSet.next() )
                {
                    plataformas.add( Plataformas.builder()
                            .withId( resultSet.getString(1) )
                            .withId_videojuego(resultSet.getString(2))
                            .withId_desarrolladora( resultSet.getString(3) )
                            .withNombre(resultSet.getString(4))
                            .withFecha_lanzamiento(resultSet.getDate(5))
                            .withFoto(resultSet.getString(6))
                            .build()
                    );
                }
            }
        } catch (SQLException sqlException)
        {
            throw new VideojuegoException((sqlException));
        }
        Mappers mappers = new Mappers();

        return mappers.toPlataforma(id, plataformas);
    }

    @Override
    public List<Plataformas> selectPlataformas() throws VideojuegoException {
        List<Plataformas> plataformas = new ArrayList<>();

        String SQL = """
                     SELECT p.id,p.id_videojuego, p.id_desarrolladora, p.nombre, p.fecha_lanzamiento, p.foto
                             FROM plataformas p
                     """;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL))
        {

            while (resultSet.next() )
            {
                plataformas.add( Plataformas.builder()
                        .withId( resultSet.getString(1) )
                        .withId_videojuego(resultSet.getString(2))
                        .withId_desarrolladora(resultSet.getString(3))
                        .withNombre( resultSet.getString(4) )
                        .withFecha_lanzamiento( resultSet.getDate(5) )
                        .withFoto(resultSet.getString(6))
                        .build()
                );
            }

        } catch (SQLException sqlException)
        {

            throw new VideojuegoException(sqlException);
        }
        return plataformas;
    }
}
