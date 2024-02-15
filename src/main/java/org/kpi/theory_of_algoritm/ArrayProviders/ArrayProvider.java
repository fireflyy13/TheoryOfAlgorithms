package org.kpi.theory_of_algoritm.ArrayProviders;


import org.kpi.theory_of_algoritm.Exception.ArrayProviderException;

import java.util.List;

public interface ArrayProvider {
    List<Float> getArray() throws ArrayProviderException;
}
