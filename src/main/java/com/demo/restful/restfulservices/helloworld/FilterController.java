package com.demo.restful.restfulservices.helloworld;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilterController {

	
	@RequestMapping(method=RequestMethod.GET, value = "/staticfilter")
	public SomeBean getSomeBean() {
		return new SomeBean("value1","value3","value3");
	}
	
	//show only  field1 , field2
	@RequestMapping(method=RequestMethod.GET, value = "/dynamicfilter1")
	public MappingJacksonValue getSomeBean2() {
		SomeBean2 bean = new SomeBean2("value1","value2","value3");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(new String[]{"field1","field2"});
		FilterProvider filter2 = new SimpleFilterProvider().addFilter("somebeanfilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(bean);
		mapping.setFilters(filter2);
	//	SomeBean2 bean2 = (SomeBean2) mapping.getValue();
		return mapping;
	}
	
	//show only field2, field3
	@RequestMapping(method=RequestMethod.GET, value = "/dynamicfilter2")
	public MappingJacksonValue getSomeBean3() {
		SomeBean2 bean = new SomeBean2("value1","value2","value3");
		
		SimpleBeanPropertyFilter propFilter = SimpleBeanPropertyFilter.filterOutAllExcept(new String[]{"field2","field3"});
		FilterProvider filter = new SimpleFilterProvider().addFilter("somebeanfilter", propFilter);
		MappingJacksonValue mapping = new MappingJacksonValue(bean);
		mapping.setFilters(filter);
		return mapping;
	}
}
