package kitchen;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

public class RecipeReportAtaskaita {
	
	protected Session em;
	
	public RecipeReportAtaskaita() {}
	
	public RecipeReportAtaskaita(  Session em  ) {
		    this.em = em;
	}
	
	 public List<RecipeReport> recipePriceRange( String kaina_nuo, String kaina_iki ) {
		 String where_from = "1";
		 if(kaina_nuo != null) {
			 where_from = "r.price > " + kaina_nuo + " and r.price < " + kaina_iki ;
		 }
		  			
		 
		  	String qw_top_patieklai =
		  				
		  			"SELECT * "
		  			+ "FROM recipe r "
		  			+ "WHERE "
		  			+ where_from
		  			
		  			;
		  	System.out.println ( qw_top_patieklai );
		    Query query = em.createNativeQuery ( qw_top_patieklai );
		    return (List<RecipeReport>) query.getResultList();
	  }	
}