package PT2019.Assignment_2;
import Controller.ConditionsListener;
import Model.Client;
import Model.Coada;
import View.*;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //InterfataUtilizator i = new InterfataUtilizator(8);
        Conditions c = new Conditions();
        ConditionsListener l = new ConditionsListener(c);
    }
}
