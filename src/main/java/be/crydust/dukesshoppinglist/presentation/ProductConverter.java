package be.crydust.dukesshoppinglist.presentation;

import be.crydust.dukesshoppinglist.business.shoppinglist.boundary.ProductBoundary;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Product;
import javax.ejb.Stateless;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author kristof
 */
@Stateless
@Named
@Slf4j
public class ProductConverter implements Converter {

    @Inject
    ProductBoundary productBoundary;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) throws ConverterException {
        if ("null".equals(string)) {
            return null;
        }
        try {
            return productBoundary.find(Long.valueOf(string));
        } catch (NumberFormatException ex) {
            throw new ConverterException(ex);
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) throws ConverterException {
        if (o == null) {
            return "null";
        }
        if (o instanceof Product) {
            return ((Product) o).getId().toString();
        }
        throw new ConverterException();
    }

}
