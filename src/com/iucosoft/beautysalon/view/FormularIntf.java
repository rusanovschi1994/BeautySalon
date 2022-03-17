package com.iucosoft.beautysalon.view;

/**
 *
 * @author Rusanovschi
 */
public interface FormularIntf<T> {

    void fillForm(T t);

    T readForm();

    void clearForm();

}
