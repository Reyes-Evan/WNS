/*
 * Carlos Andrés Reyes Evangelista          157068  este bato es un pendejo
 * Dulio Paolo Caggiano Amand               156449
 * Jose Miguel Garcia Reyes                 155871
 * Jordi Omar Ponce Bonfil                  155718
 * Juan Carlos                              155713
 * Ingeniería en Sistemas Computacionales. UDLAP.
 */

package wns;

import interfaces.MainInterface;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;




public class Main {
    static void cls () {
        System.out.println(new String(new char[50]).replace("\0", "\r\n"));
    }
    
    static void pause () throws IOException {
        System.out.print("\n\n\t Presione Enter para continuar... ");
        System.in.read();
    }
    
    static void menu () {
        cls();
        System.out.println("\t\tWNS SW");
        System.out.println("\n   ¿Qué deseas hacer?");
        System.out.println("\n\t1. Dar de alta un producto");
        System.out.println("\n\t2. Dar de baja un producto");
        System.out.println("\n\t3. Buscar un producto");
        System.out.println("\n\t4. Desplegar todos los productos");
        System.out.println("\n\t5. Compactar archivo");
        System.out.println("\n\t6. Salir");
        System.out.print("\n     > ");
    }
    
    static void console () throws IOException {
        //Set of the file to be used in this program
        java.io.File file               = new java.io.File("products");
        //Set of the RAF linked to the File file with read and write properties
        RandomAccessFile raf    = new RandomAccessFile(file, "rw" );

        Scanner cin             = new Scanner(System.in);
        
        //Instance of our Archivo class to be used in this session
        File productsFile = new File(raf);

        //If the file is new, we establish our choosen EOF symbol [(unsigned byte) 255 = (signed byte, int) -1 = (char) ÿ)] immediately
        if (raf.length() == 0)
            raf.write(255);

        byte x = 0;
        while (x != 6) {
            menu();
            x = cin.nextByte();
            
            if (x < 0 || x > 6)
                System.out.println("\n\tOpción inválida. Reintente.");
            else
                switch (x) {
                    
                    case 1:
                        String nombre, categoria, subcategoria, descripcion, locacion;
                        int disponibilidad;
                        double precio;
                        
                        cls();
                        System.out.println("\n Alta de productos");
                        System.out.print("\nIngrese nombre de producto: ");
                        nombre    = cin.next();
                        System.out.print("\nTeclee el nombre de categoria: ");
                        categoria      = cin.next();
                        System.out.print("\nTeclee el nombre de subcategoria: ");
                        subcategoria      = cin.next();
                        System.out.print("\nEscriba la descripcion: ");
                        descripcion      = cin.next();
                        System.out.print("\nDigite la localización del producto: ");
                        locacion      = cin.next();
                        System.out.print("\nInserte precio: ");
                        precio       = cin.nextDouble();
                        System.out.print("\nIngrese la disponibilidad de este producto: ");
                        disponibilidad  = cin.nextInt();
                        
                        productsFile.insert(new Product (nombre, categoria, subcategoria, descripcion, locacion, precio, disponibilidad));
                        
                        System.out.print("\n\n\t¡Producto escrito con éxito!");
                        pause();
                    break;
                    
                    case 2:
                        cls();
                        System.out.println("\n Baja de registros");
                        System.out.print("\n\n Ingrese la clave a eliminar: ");
                        System.out.println((productsFile.delete(cin.next()) ? "\nRegistro eliminado exitosamente" : "\nRegistro inexistente. Imposible eliminar."));
                                
                        pause();
                    break;
                    
                    case 3:
                        Product temp;
                        
                        cls();
                        System.out.println("\n Búsqueda de registros");
                        System.out.print("\n  Ingrese el nombre a buscar: ");
                        temp = productsFile.search(cin.next());
                                
                        System.out.println((temp == null ? "Registro no encontrado en el archivo." : "¡Registro encontrado!\n\n"));
                        if (temp != null)
                            temp.display();
                        pause();
                    break;
                    
                    
                    case 4:
                        productsFile.displayProducts();
                        pause();
                    break;
                    
                    case 5:
                        System.out.println((productsFile.compact() ? "¡Archivo compactado con éxito!" : "No hay nada para compactar."));
                        pause();
                    break;
                    
                    case 6:
                        System.out.println("Comprimiendo archivo...\nCerrando archivo...\nFinalizando ejecución...");
                        productsFile.compact();
                        raf.close();
                        System.exit(0);
                    break;
                }
        }
    }
    
    public static void main (String[] args) {
//        Interface interfaz = new Interface();
//        interfaz.setVisible(true);
        MainInterface.main(args);
        /*try {
            console();
        }
        catch (IOException e) {
            
        }*/
        
//        System.out.print("Hola mundo");
    }
    
}
