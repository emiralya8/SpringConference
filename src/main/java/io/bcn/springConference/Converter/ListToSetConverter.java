package io.bcn.springConference.Converter;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;
import io.bcn.springConference.model.Conference;
import org.hibernate.LazyInitializationException;

import java.util.*;

public class ListToSetConverter implements Converter<Set<Conference>, List<Conference>> {
    @Override
    public Result<List<Conference>> convertToModel(Set<Conference> conferences, ValueContext valueContext) {
        return Result.ok(conferences != null ? new ArrayList<>(conferences) : null);
    }

    @Override
    public Set<Conference> convertToPresentation(List<Conference> conferences, ValueContext valueContext) {
        try{
            return conferences != null ? new HashSet<>(conferences) : null;
        }catch(LazyInitializationException e){
            return new Set<Conference>() {

                @Override
                public int size() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public boolean contains(Object o) {
                    return false;
                }

                @Override
                public Iterator<Conference> iterator() {
                    return null;
                }

                @Override
                public Object[] toArray() {
                    return new Object[0];
                }

                @Override
                public <T> T[] toArray(T[] ts) {
                    return null;
                }

                @Override
                public boolean add(Conference conference) {
                    return false;
                }

                @Override
                public boolean remove(Object o) {
                    return false;
                }

                @Override
                public boolean containsAll(Collection<?> collection) {
                    return false;
                }

                @Override
                public boolean addAll(Collection<? extends Conference> collection) {
                    return false;
                }

                @Override
                public boolean retainAll(Collection<?> collection) {
                    return false;
                }

                @Override
                public boolean removeAll(Collection<?> collection) {
                    return false;
                }

                @Override
                public void clear() {

                }
            };
        }
    }
}
