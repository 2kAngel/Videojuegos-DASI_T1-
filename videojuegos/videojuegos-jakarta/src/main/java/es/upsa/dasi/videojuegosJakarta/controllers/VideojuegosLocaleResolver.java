package es.upsa.dasi.videojuegosJakarta.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.mvc.locale.LocaleResolver;
import jakarta.mvc.locale.LocaleResolverContext;
import jakarta.ws.rs.core.PathSegment;
import jakarta.ws.rs.core.UriInfo;

import java.util.Locale;

@RequestScoped
public class VideojuegosLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(LocaleResolverContext localeResolverContext)
    {
        UriInfo uriInfo = localeResolverContext.getUriInfo();
        PathSegment languagePath = uriInfo.getPathSegments().get(0);
        String language = languagePath.getPath();
        return Locale.forLanguageTag(language);
    }
}
