package com.stackroute.streams;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class has various methods to do stream operations on person collection
 */
public class PersonStreamOperations {

    /**
     * sorts the person list alphabetically in uppercase
     * Returns Empty Optional if the given list is null or empty
     *
     * @param personList
     * @return
     */
	public Optional<List<String>> getPersonListSortedByNameInUpperCase(List<String> personList) {

		if (personList == null || personList.isEmpty()) {

			return Optional.empty();

		}

		return Optional.of(personList.stream().filter(s -> !s.trim().isEmpty()).map(String::toUpperCase).sorted()
				.collect(Collectors.toList()));

	}

    /**
     * Sorts the distinct person names in descending order
     * Returns empty set if the given list is empty or null
     *
     * @param personList
     * @return
     */

    public Set<String> getDistinctPersonNamesSortedInDescendingOrder(List<String> personList) {
    	
    	if (personList == null || personList.isEmpty()) {
            return Collections.emptySet(); 
        }
    	return personList.stream()
    			 .filter(s -> !s.trim().isEmpty()) 
                 .distinct()               
                 .sorted(Comparator.reverseOrder()) 
                 .collect(Collectors.toCollection(() -> new LinkedHashSet<>()));  //LinkedHashSet is used to maintain the sorted order to get getDistinctPersonNames
   
    
    
    }

    /**
     * Returns "Person found" if the person searched is available in the list or else returns "Person not found"
     * Returns "Give proper input not null" if the given list or name to search is null
     *
     * @param personList
     * @return
     */
	public String searchPerson(List<String> personList, String nameToSearch) {

		if (personList == null || nameToSearch == null || personList.isEmpty()) {
			return "Give proper input not null";
		}
		String capitalize = capitalizeSearchName(nameToSearch);

		if (personList.contains(capitalize)) {
			return "Person found";
		} else {
			return "Person not found";
		}

	}

	public  String capitalizeSearchName(String str) {
		
		if (str == null || str.length() == 0) {
			
			return str;
		}

		return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
	}
    
    
    /**
     * Filters the list whose name length is greater than five and sorts by name length
     * Returns empty list if the given list is empty or null
     *
     * @param personList
     * @return
     */

    public List<String> getPersonListSortedByLengthWithNameLengthGreaterThanFive(List<String> personList) {
      
    	 if (personList == null || personList.isEmpty()) {
    		 
             return Collections.emptyList();
             
         }
    	return personList.stream()
                .filter(personName -> personName.length() > 5)  
                .sorted((pn1, pn2) -> Integer.compare(pn1.length(), pn2.length())) 
                .collect(Collectors.toList());
    	
    	
    }

    /**
     * Returns the person name having maximum age from the given Map<String,Integer> having name as key and age as value
     * Returns "Give proper input not null" if the given map is empty or null
     *
     * @param personMap
     * @return
     */

	public String getPersonByMaxAge(Map<String, Integer> personMap) {

		if (personMap == null || personMap.isEmpty()) {

			return "Give proper input not null";

		}
		return personMap.entrySet().stream()
								   .max((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue()))
								   .map(entry -> entry.getKey()).get();

	}

}
