import java.util.* ;
import java . nio . charset . Charset ; 
import java . io . * ; 
import java . nio . file .*; 
import  static java . lang . System .*;

  class  HelloWorld  { 
    public  static  void main ( String [] args )  throws IOException
    { 
        Path source =  Paths . get ( args [ 0 ]); 
        Charset charset =  Charset . forName ( "UTF-8" ); 
        try  { 
           for ( String line :  Files . readAllLines ( source , charset ))  { 
               out . println ( line ); 
           }
           throw new  EOFException ("simon");
        }  catch ( IOException ex )  { 
           err . println ( ex . getMessage ()); 
            ex.printStackTrace();
           //throw ex;
        } 
    } 
}
  class  Average  { 
    public  static  void main ( String [] args )  { 
        Scanner scanner =  new  Scanner ( System . in ); 
        double sum =  0 ; 
        int count =  0 ; 
        int number ; 
        while ( true )  { 
            number = scanner . nextInt ( ); 
            if ( number ==  0 )  { 
                break ; 
            } 
            sum += number ; 
            count ++; 
        } 
        System . out . printf ( "ƽ��%.2f%n" , sum / count ); 
    } 
}


  class  Average2  { 
    public  static  void main ( String [] args )  { 
        try  { 
            Scanner scanner =  new  Scanner ( System . in ); 
            double sum =  0 ; 
            int count =  0 ; 
            int number ; 
            while  ( true )  { 
                number = scanner . nextInt (); 
                if  ( number ==  0 )  { 
                    break ; 
                } 
                sum += number ; 
                count ++; 
            } 
            System . out . printf ( "ƽ��%.2f%n " , sum / count ); 
        }  catch  ( InputMismatchException ex )  { 
            System . out . println ( "������������" ); 
        } 
    } 
}


  class  Average3  { 
    public  static  void main ( String [] args )  { 
        Scanner scanner =  new  Scanner ( System . in ); 
        double sum =  0 ; 
        int count =  0 ; 
        int number ; 
        while  ( true )  { 
            try  { 
                number = scanner . nextInt (); 
                if  ( number ==  0 )  { 
                    break ; 
                } 
                sum += number ; 
                count ++; 
            }  catch  ( InputMismatchException ex )  { 
                System . out . printf ( "�Թ����������룺%s%n" , scanner . next ()); 
            } 
        } 
        System . out . printf ( "ƽ��%.2f%n" , sum / count ); 
    } 
}


interface Action { 
    void execute(); 
} 

class Some implements Action {  
  public void execute() {         System.out.println("��һЩ����");     }
   }
 class Main { 
    public static void main(String[] args) { 
        Action action = new Some(); 
        action.execute(); 
    } 
}


 class  Main2  {     
    public  static  void main ( String [] args )  { 
        try ( ResourceSome some =  new  ResourceSome (); 
             ResourceOther other =  new  ResourceOther ())  { 
            some . doSome (); 
            other . doOther (); 
        }  catch ( Exception ex )  { 
            ex . printStackTrace (); 
        } 
    } 
}

class  ResourceSome  implements  AutoCloseable  { 
    void doSome ()  { 
        System . out . println ( "��һЩ��" ); 
    } 
    @Override 
    public  void close ()  throws  Exception  { 
        System . out . println ( "��ԴSome���ر�" ); 
    } 
}

class  ResourceOther  implements  AutoCloseable  { 
    void doOther ()  { 
        System . out . println ( "��������" ); 
    } 
    @Override 
    public  void close ()  throws  Exception  { 
        System . out . println ( "��ԴOther���ر�" ); 
    } 
}

class  Main3  { 
    public  static  void main ( String [] args )  { 
        try  { 
            c (); 
        }  catch ( NullPointerException ex )  { 
            ex . printStackTrace (); 
        } 
    }   

    static  void c ()  { 
        try  { 
            b (); 
        }  catch ( NullPointerException ex )  { 
            ex . printStackTrace (); 
            Throwable t = ex . fillInStackTrace (); 
            throw  ( NullPointerException ) t ; 
        } 
    } 

    static  void b ()  { 
        a (); 
    } 

    static  String a ()  { 
        String text =  null ; 
        return text . toUpperCase (); 
    }   
}