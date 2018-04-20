/*
 * Carlos Andrés Reyes Evangelista          157068
 * Dulio Caggiano Armani                    15
 * José Miguel García                       15
 * Juan Carlos Sánchez Ruiz de Chávez       15
 * Jorge Omar Ponce Bonfil                  15
 * Ingeniería en Sistemas Computacionales. UDLAP.
 */

package wns;

import java.io.RandomAccessFile;
import java.io.IOException;
import javax.swing.*;
import org.netbeans.lib.awtextra.*;
import resources.Style;

import interfaces.MainInterface;
import java.awt.Color;

public class Product {
    public boolean ACTIVE       = true;
    public boolean NOT_ACTIVE   = false;
    
    private boolean status;
    private int     nextInFile;
    
    /* PRODUCT ATTRIBUTES  */
    private String  name;
    private String  description;
    private String  category;
    private String  subcategory;
    private String  location;
    private double  price;
    private int     availability;
    private ImageIcon image;
    
    /* GRAPHIC PRODUCT ATRIBUTES */
    private JPanel pane;
    private JLabel nameLbl;
    private JLabel categoryLbl;
    private JLabel priceLbl;
    private JLabel availabilityLbl;
    private JLabel imageLbl;
    
    public Product () {}
    
    public Product (String name, String category, String subcategory, String description, String location, double price, int availability) {
        this.name           = name;
        this.category       = category;
        this.subcategory    = subcategory;
        this.description    = description;
        this.location       = location;
        this.price          = price;
        this.availability   = availability;
        status              = ACTIVE;
        nextInFile          = -1;
    }
    
    
    /* GETTERS METHODS      */

    /**
     * Returns the name of this product.
     * @return
     */
    public String getName () {
        return name;
    }
    
    /**
     * Returns the price of this product.
     * @return
     */
    public double getPrice () {
        return price;
    }
    
    /**
     * Returns the description of this product.
     * @return
     */
    public String getDescription () {
        return description;
    }
    
    /**
     * Returns the category of this product.
     * @return
     */
    public String getCategory () {
        return category;
    }
    
    /**
     * Returns the subcategory of this product.
     * @return
     */
    public String getSubcategory () {
        return subcategory;
    }
    
    /**
     * Returns the availability of this product.
     * @return
     */
    public int getAvailability () {
        return availability;
    }
    
    /**
     * Returns the location in the warehouse of this product.
     * @return
     */
    public String getLocation () {
        return location;
    }
    
    public int getNext () {
        return nextInFile;
    }
    
    public boolean getStatus () {
        return status;
    }
    
    /* SETTERS METHODS  */

    /**
     * Change the name of this product.
     * Name cannot be just one letter.
     * @param newName
     */
    public void setName (String newName) {
        if (newName.length() <= 1)
            System.out.println("\tName too short!");
        else
            name = newName;
    }
    
    /**
     * Change the price of this product.
     * Price cannot be a negative or zero number.
     * @param newPrice
     */
    public void setPrice (int newPrice) {
        if (newPrice <= 0)
            System.out.println("\tPrice should be nonnegative!");
        else
            price = newPrice;
    }
    
    /**
     * Change the description for this product.
     * @param newDescription
     */
    public void setDescription (String newDescription) {
        description = newDescription;
    }
    
    /**
     * Change the category of this product.
     * Pretend to be a Enum with next updates.
     * @param newCategory
     */
    public void setCategory (String newCategory) {
        category = newCategory;
    }
    
    /**
     * Change the subcategory of this product.
     * Pretend to be a Enum with next updates.
     * @param newSubcategory
     */
    public void setSubcategory (String newSubcategory) {
        subcategory = newSubcategory;
    }
    
    /**
     * Change the availability for this product. The availability will be added 
     * with the reason of change given by the parameter change.
     * If the availability is changed negatively, write the parameter as a negative number.
     * @param change
     */
    public void setAvailability (int change) {
        if (availability + change < 0)
            System.out.println("\nAvailability can't be less than 0!");
        else
            availability += change;
    }
    
    /**
     * Change the location in the warehouse for this product.
     * @param newLocation
     */
    public void setLocation (String newLocation) {
        location = newLocation;
    }
    
    /**
     * Change the direction in bytes of the next register in the file that storage this
     * @param n
     */
    public void setNext (int n) {
        nextInFile = n;
    }
    
    /**
     * If different, change the 
     * @param b
     */
    public void setStatus (boolean b) {
        if (b != status)
            status = b;
    }
    
    /*                                                  /*
    *****************************************************
    /*                                                  */
    
    public int length () {
        return name.length() + description.length() + category.length() + subcategory.length() + location.length() + 2 * (Integer.SIZE / 8) + (Double.SIZE / 8) + 6;
    }
    
    private String readString (RandomAccessFile raf) throws IOException {
        int strLength = raf.readByte();
        byte [] b       = new byte[strLength];
        raf.read(b);
        return new String (b);
    }
    
    public void read (int p, RandomAccessFile raf) throws IOException {
        raf.seek(p);
        
        status      = raf.readBoolean();
        nextInFile  = raf.readInt();
        name        = readString(raf);
        category    = readString(raf);
        subcategory = readString(raf);
        location    = readString(raf);
        description = readString(raf);
        price       = raf.readDouble();
        availability= raf.readInt();
    }
    
    public void write (RandomAccessFile raf) throws IOException {
        int currentFileSize = (int) raf.length();
        raf.writeBoolean(status);
        if (nextInFile == -1)
            raf.writeInt(currentFileSize + this.length() - 1);
        else
            raf.writeInt(nextInFile);
        raf.writeByte(name.length());
        raf.write(name.getBytes());
        raf.writeByte(category.length());
        raf.write(category.getBytes());
        raf.writeByte(subcategory.length());
        raf.write(subcategory.getBytes());
        raf.write(location.length());
        raf.write(location.getBytes());
        raf.writeByte(description.length());
        raf.write(description.getBytes());
        raf.writeDouble(price);
        raf.writeInt(availability);
    }
    
    public void display () {
        System.out.println("|--------------------------------------------------------------------|");
        System.out.println("| Nombre de producto:\t\t"        + name);
        System.out.println("| Categoría de producto:\t"     + category);
        System.out.println("| Subcategoría de producto:\t"  + subcategory);
        System.out.println("| Descripción del producto:\t"  + description);
        System.out.println("| Localización del producto:\t" + location);
        System.out.println("| Precio del producto:\t\t$ "       + price);
        System.out.println("| Disponibilidad del producto:\t"    + availability);
        System.out.println("|--------------------------------------------------------------------|");
    }
    
    public JPanel exportAsPane() {
        pane    = new JPanel (new AbsoluteLayout());
        
        pane.setOpaque(false);
        
        imageLbl = new JLabel ("IMG"); // IN THE FUTURE, HERE WILL BE THE FOLLOWING CODE: imageLbl = new JLabel (new ImageIcon(getClass.getResource("/resources/" + name + extension));
        pane.add(imageLbl, new AbsoluteConstraints(0, 0, 50, 60));
        
        nameLbl = new JLabel (getName());
        nameLbl.setForeground(Style.foreground);
        pane.add(nameLbl, new AbsoluteConstraints(50, 0, 100, -1));
        
        categoryLbl = new JLabel (getCategory());
        categoryLbl.setForeground(Style.foreground);
        pane.add(categoryLbl, new AbsoluteConstraints(50, 15, 100, -1));
        
        availabilityLbl = new JLabel (getAvailability() + " en inventario");
        availabilityLbl.setForeground(Style.foreground);
        pane.add(availabilityLbl, new AbsoluteConstraints(50, 30, 100, -1));
        
        priceLbl = new JLabel ("$ " + String.format("%3f", getPrice()));
        priceLbl.setForeground(Style.foreground);
        pane.add(priceLbl, new AbsoluteConstraints(50, 45, 100, -1));
//        image.setImage(image.);
        
        return pane;
    }
}
