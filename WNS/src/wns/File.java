/*
 * Carlos Andrés Reyes Evangelista
 * 157068
 * Ingeniería en Sistemas Computacionales. UDLAP.
 */
package wns;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Reyes
 */
public class File {
    private final RandomAccessFile raf;
    
    /**
     * Send to this instance the RAF to use in the project
     * @param raf
     */
    public File (RandomAccessFile raf){
        this.raf = raf;
    }
    
    /* 
    auxiliar method that searches and return the position in bytes
    of the ÿ symbol, indicating that the useful bytes are before the position returned
    */
    private int searchEOF () throws IOException{
        Product temp = new Product();
        int x = 0;
        
        raf.seek(x);
        while (raf.readByte() != -1) {
            temp.read(x, raf);
            
            x = temp.getNext();
        }
        return (int) raf.getFilePointer() - 1;
    }
        
    /**
     * Searchs for the ÿ byte and write the register registro
     * exactly in that position, then writes again the ÿ symbol at the end of this
     * @param registro
     * @throws IOException
     */
    public void insert (Product product) throws IOException {
        raf.seek(searchEOF());
        product.write(raf);
        raf.writeByte(255);
    }
    
    /**
     * Searchs for the string s in the client name field in every single register in the file 
     * Returns pointer to the first ocurrence if founded or null pointer otherwise
     * @param s
     * @return
     * @throws IOException
     */
    public Product search (String s) throws IOException{
        Product    temp  = new Product();
        int         next = 0;
        
        raf.seek(0);
        while (raf.readByte() != -1) {
            temp.read(next, raf);
            
            if (temp.getName().equals(s))
                return temp;
            else
                next = temp.getNext();
            raf.seek(next);
        }
        return null;
    }
    
    /**
     * Try to delete the register with name toDelete. If this register doesn't exists then return false
     * otherwise, this will mark the register as eliminated and returns true
     * @param aEliminar
     * @return
     * @throws IOException
     */
    public boolean delete (String toDelete) throws IOException {
        Product temp = search (toDelete);
        
        if (temp == null)
            return false;
        
        temp.setStatus(temp.NOT_ACTIVE);
        raf.seek(temp.getNext() - temp.length());
        raf.writeByte(0);

        return true;
    }
    
    /*
    auxiliar and recursive method called when compacting. Its function is rewrite all the active registers overwriting the deleted ones.
    It's functionality it's simple: it's called with two parameters, @current represent the bytes position in that the next active register can begin to overwrite
    whereas, @next represent the bytes position of the next register to considerate.
    If the byte in the @next position equals -1, it means that there are no more registers to move, so this method just writes the -1 symbol (-1 (signed int) = 255 (unsigned byte) = 11111111 (bits) = ÿ (char))
    Else, this has to verificate that the register in @next position is active, if it isn't then just recall with the next register
    If actually the @next register is active then calculate in what position this register will end to be wrote, rewrites in @current position and recalls with the new @current and @next position
    */
    private void compact (int current, int next) throws IOException {
        Product nextP = new Product ();
        int newNext;
        
        raf.seek(next);
        
        if (raf.readByte() != -1) {
            nextP.read(next, raf);
            next = nextP.getNext();
            
            if (!nextP.getStatus())
                compact(current, next);
            else {
                newNext = current + nextP.length();
                nextP.setNext(newNext);
                raf.seek(current);
                nextP.write(raf);

                compact(newNext, next);
            }
        }
        
        else {
            raf.seek(current);
            raf.write(255);
        }
            
        
    }
    
    /**
     * This method can be called in the main menu in order to free memory in the file or will be automatically called when execution ends
     * If there aren't deleted registers, this just returns false
     * Otherwise, this will found the first deleted register and will call subirRegistros with that position as @current and the next as @next
     * When subirRegistros finally finish its job, this method will truncate the file at the next position where the -1 symbol were and returns true
     * @return
     * @throws IOException
     */
    public boolean compact () throws IOException {
        Product temp = new Product();
        
        int     current = 0;
        boolean somethingToCompact = false;
        
        raf.seek(current);
        while (raf.readByte() != -1 && !somethingToCompact) {
            temp.read(current, raf);
            
            if (temp.getStatus())
                current = temp.getNext();
            else {
                compact(current, temp.getNext());
                somethingToCompact = true;
            }
        }
        
        raf.setLength(searchEOF() + 1);
        return somethingToCompact;
    }

    /**
     * Display in console every active register in this file
     * @throws IOException
     */
    public void displayProducts () throws IOException {
        Product product = new Product();
        
        int next = 0;
        raf.seek(next);
        
        System.out.println();
        while (raf.readByte() != -1) {
            product.read(next, raf);
            
            if (product.getStatus())
                product.display();
            
            next = product.getNext();
        }
    }
    
    /**
     * Return an ArrayList with all the current active products in the database
     * @return
     * @throws IOException
     */
    public ArrayList allProducts () throws IOException {
        ArrayList <Product> products  = new ArrayList ();
        int next            = 0;
        
        raf.seek(next);
        while (raf.readByte() != -1) {
            Product product     = new Product();
            product.read(next, raf);
            
            if (product.getStatus())
                products.add(0, product);
            
            next = product.getNext();
        }
        
        return products;
    }
    
    /*
        SEARCH  METHODS FOR INVENTORY
    */
    
    
    public ArrayList searchByName(String name) throws IOException{
        ArrayList<Product> inventory = new ArrayList();
        ArrayList<Product> result = new ArrayList();
        inventory = allProducts();
        
        for(int i = 0; i < inventory.size() ; i++){
            if(inventory.get(i).getName().trim().equals(name))
                result.add(inventory.get(i));
        }
   
       return result;
        
    }
    
    public ArrayList searchByCategory(String category) throws IOException{
        ArrayList<Product> inventory = new ArrayList();
        ArrayList<Product> result = new ArrayList();
        inventory = allProducts();
        
        for(int i = 0; i < inventory.size() ; i++){
            if(inventory.get(i).getCategory().trim().equals(category))
                result.add(inventory.get(i));
        }
       return result;
    }
    
    public ArrayList searchBySubCategory(String subcatergory) throws IOException{
        ArrayList<Product> inventory = new ArrayList();
        ArrayList<Product> result = new ArrayList();
        inventory = allProducts();
        
        for(int i = 0; i < inventory.size() ; i++){
            if(inventory.get(i).getSubcategory().trim().equals(subcatergory))
                result.add(inventory.get(i));
        }
        
       return result;
    }

    public ArrayList<Product> searchByPriceRange(int minPrice, int maxPrice) throws IOException {
        ArrayList<Product> inventory = new ArrayList();
        ArrayList<Product> result = new ArrayList();
        inventory = allProducts();
        double price = 0;
        
        for(int i = 0; i < inventory.size() ; i++){
            price = inventory.get(i).getPrice();
            if((price>=minPrice) && (price<=maxPrice))
                result.add(inventory.get(i));
        }
        
       return result;
    }

    public ArrayList<Product> searchByAvalRange(int minAval, int maxAval) throws IOException {
        ArrayList<Product> inventory = new ArrayList();
        ArrayList<Product> result = new ArrayList();
        inventory = allProducts();
        int aval = 0;
        
        for(int i = 0; i < inventory.size() ; i++){
            aval = inventory.get(i).getAvailability();
            if((aval>=minAval) && (aval<=maxAval))
                result.add(inventory.get(i));
        }
        
       return result;
    }
    
}
