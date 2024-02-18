package org.kpi.theory_of_algoritm.ArrayProviders;


import org.kpi.theory_of_algoritm.Exception.ArrayProviderException;

import java.util.List;

/*
Asking for array with floating point numbers
and throwing an error in case we fail to
retrieve it
 */
public interface ArrayProvider {
    List<Float> getArray() throws ArrayProviderException;

}
