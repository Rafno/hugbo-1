package project.persistence.entities;

import javax.persistence.*;

/**
 * The class for the Medicine itself.
 * The system generates a table schema based on this class for this entity.
 * Be sure to annotate any entities you have with the @Entity annotation.
 */
@Entity
@Table(name = "medicine") // If you want to specify a table name, you can do so here
public class Medicine {

    // Declare that this attribute is the id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String active_ingredient;
    private String pharmaceutical_form;
	private String strength;
	private String atc_code;
	private String legal_status;
	private String mah;
	private String other_info;
	private String marketed;
	private String ma_issued;

    // Notice the empty constructor, because we need to be able to create an empty PostitNote to add
    // to our model so we can use it with our form
    public Medicine() {
    }
	
    public Medicine(String name, String active_ingredient, String pharmaceutical_form,
					String strength, String atc_code, String legal_status, String mah,
					String other_info, String marketed, String ma_issued) {
	
		this.name = name;
		this.active_ingredient = active_ingredient;
		this.pharmaceutical_form = pharmaceutical_form;
		this.strength = strength;
		this.atc_code = atc_code;
		this.legal_status = legal_status;
		this.mah = mah;
		this.other_info = other_info;
		this.marketed = marketed;
		this.ma_issued = ma_issued;
	}
	
	
	public String getName_medicine()
	{
		return name;
	}
	
	public void setName_medicine(String name)
	{
		this.name = name;
	}
	
	public String getActive_ingredient()
	{
		return active_ingredient;
	}
	
	public void setActive_ingredient(String active_ingredient)
	{
		this.active_ingredient = active_ingredient;
	}
	
	public String getPharmaceutical_form()
	{
		return pharmaceutical_form;
	}
	
	public void setPharmaceutical_form(String pharmaceutical_form)
	{
		this.pharmaceutical_form = pharmaceutical_form;
	}
	
	public String getStrength()
	{
		return strength;
	}
	
	public void setStrength(String strength)
	{
		this.strength = strength;
	}
	
	public String getAtc_code()
	{
		return atc_code;
	}
	
	public void setAtc_code(String atc_code)
	{
		this.atc_code = atc_code;
	}
	
	public String getLegal_status()
	{
		return legal_status;
	}
	
	public void setLegal_status(String legal_status)
	{
		this.legal_status = legal_status;
	}
	
	public String getMah()
	{
		return mah;
	}
	
	public void setMah(String mah)
	{
		this.mah = mah;
	}
	
	public String getOther_info()
	{
		return other_info;
	}
	
	public void setOther_info(String other_info)
	{
		this.other_info = other_info;
	}
	
	public String getMarketed()
	{
		return marketed;
	}
	
	public void setMarketed(String marketed)
	{
		this.marketed = marketed;
	}
	
	public String getMa_issued()
	{
		return ma_issued;
	}
	
	public void setMa_issued(String ma_issued)
	{
		this.ma_issued = ma_issued;
	}
	
	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	// This is for easier debug.
	@Override
	public String toString() {
		return String.format(
			"Medicine name[name_medicine=%s, id=%s]",
			name,id);
	}
}
