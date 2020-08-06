package cn.arp.statistic.tools;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;

public class PredicateBuilder<T> {
	private List<Predicate> predicates = new ArrayList<Predicate>();
	private Root<T> root;
	private CriteriaBuilder cb;
	private PredicateBuilder<T> parent=null;

	public PredicateBuilder(CriteriaBuilder cb, Root<T> root) {
		this.cb = cb;
		this.root = root;
	}
	
	public PredicateBuilder(CriteriaBuilder cb, Root<T> root, PredicateBuilder<T>parent) {
		this.cb = cb;
		this.root = root;
		this.parent = parent;
	}
	
	
	public PredicateBuilder<T> subBuilder(){
		return new PredicateBuilder<T>(cb, root, this);
	}
	
	public PredicateBuilder<T> startWith(String attribute, String value){
		if (StringUtils.isNotEmpty(value)){
			predicates.add(cb.like(root.get(attribute), value+"%"));
		}
		return this;
	}
	
	public PredicateBuilder<T> endsWith(String attribute, String value){
		if (StringUtils.isNotEmpty(value)){
			predicates.add(cb.like(root.get(attribute), "%"+value));
		}
		return this;
	}

	public PredicateBuilder<T> include(String attribute, String value){
		if (StringUtils.isNotEmpty(value)){
			predicates.add(cb.like(root.get(attribute), "%"+value+"%"));
		}
		return this;
	}
	
	public PredicateBuilder<T> like(String attribute, String value) {
		if (StringUtils.isNotEmpty(value)) {
			predicates.add(cb.like(root.get(attribute), value));
		}
		return this;
	}

	public PredicateBuilder<T> equals(String attribute, Object value) {
		if (value != null) {
			predicates.add(cb.equal(root.get(attribute), value));
		}
		return this;
	}
	public PredicateBuilder<T> in(String attribute, List<String> values) {
		if (values!=null){
			In<String> in = cb.in(root.get(attribute));
			for (String value:values){
				in.value(value);
			}
			predicates.add(in);
		}
		return this;
	}

	public PredicateBuilder<T>  ge(String attribute, Date date) {
		if (date!=null){
			predicates.add(cb.greaterThanOrEqualTo(root.get(attribute), date));
		}
		return this;
	}
	
	public PredicateBuilder<T> equal(String attribute, String value) {
		if (StringUtils.isNotEmpty(value)) {
			predicates.add(cb.equal(root.get(attribute), value));
		}
		return this;
	}
	public Predicate and(){
		Predicate result ;
		switch(predicates.size()){
		case 0:
			result= null;
			break;
		case 1:
			result= predicates.get(0);
			break;
		default:
			result= cb.and(predicates.toArray(new Predicate[predicates.size()]));
			break;
		}
		
		if (parent!=null){
			parent.addPredicate(result);
		}
		return result;
	}
	
	public Predicate or(){
		Predicate result;
		switch(predicates.size()){
		case 0:
			result= null;
			break;
		case 1:
			result= predicates.get(0);
			break;
		default:
			result= cb.or(predicates.toArray(new Predicate[predicates.size()]));
			break;
		}
		if (parent!=null){
			parent.addPredicate(result);
		}
		return result;
	}

	public PredicateBuilder<T> isNull(String attribute) {
		predicates.add(cb.isNull(root.get(attribute)));
		return this;
	}

	public PredicateBuilder<T> addPredicate(Predicate predicate) {
		if (predicate!=null){
			predicates.add(predicate);
		}
		return this;
	}

	public PredicateBuilder<T> equalOrNull(String attribute, String value) {
		if (StringUtils.isEmpty(value)) {
			predicates.add(cb.isNull(root.get(attribute)));
		} else {
			predicates.add(cb.equal(root.get(attribute), value));
		}
		return this;
	}

	public PredicateBuilder<T> lessThanOrEqualTo(String attribute, Date effectiveDate) {
		if (effectiveDate!=null){
			predicates.add(cb.lessThanOrEqualTo(root.get(attribute), effectiveDate));
		}
		return this;
	}

	public PredicateBuilder<T> greaterThanOrEqualTo(String attribute, Date effectiveDate) {
		if (effectiveDate!=null){
			predicates.add(cb.greaterThanOrEqualTo(root.get(attribute), effectiveDate));
		}
		return this;
		
	}

}
