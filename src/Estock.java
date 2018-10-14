import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import java.io.InputStream;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Canvas;

/**
 * @author Yash Jain
 *         Educational Stock Market;
 */
public class Estock {

    protected Shell shell;

    Display display = Display.getDefault();
    private Label total;
    int totalMoney = 10000;
    int totalPortfolio = 0; 
    int ap = 0; 
    int mp = 0; 
    int cp = 0; 
    int gp = 0; 
    int fp = 0;
    int gameb = 0; 
    double[] appleprice = {223.62,225.55,214.20,214.45,0};
    double[] microprice = {7.8,9.5,9.81,10.3,0};
    double[] capitalprice = {96.19,96.20,93.90,90.96,0};
    double[] googleprice = {1150.00,1136.00,1079.28,1090.74,0};
    double[] facebookprice = {157.59,157.03,149.90,153.35,0};
    int pos = 0;
    int minus = 92; 
    String stockname = "";
    int apple = 0; 
    int micro = 0; 
    int capital = 0; 
    int google = 0; 
    int face = 0;

    String[] day1 = {"Day 1 Analysis","Good Job! You were able to realize that you should\nhold off on investing heavily based on small upticks.\nYou are risk averse.","You invested a decent amount into stocks.\nYou are an small risk taker.","You are an avid risk taker. You should be\nmore careful when you invest in stocks.\nDon't invest heavily just because you\nsee an uptick."};
    String[] day2 = {"Day 2 Analysis","Good Job! You pulled/held off on investing in tech companies.\nYou read the news and reacted actively to the data.","You invested in tech companies even\nthough the interest rate increased. Be more careful\nand adapt to given information."};
    String[] day3 = {"Day 3 Analysis","Good Job! You recognized which companies you\nshould invest in and which you should not.","You didn't start to switch your stocks to\nthe non-tech company. You knew that tech was falling so\nyou should sell and saveyour companies' money."};
    String[] day4 = {"Day 4 Analysis","Good Job! You were able to detect the trend\nby looking at the graph and see that you should invest in Youngevity.","A trade war means that those companies incurring oversees\nexpenses which makes investors nervous.\nYou should sell from these companies before it drops.\nYou also should have invested in Youngevity."};
    
    int day1w = 0; 
    int day2w = 0; 
    int day3w = 0; 
    int day4w = 0; 
    
    /**
     * Launch the application.
     * 
     * @param args
     */
    public static void main(String[] args) {
        try {
            Estock window = new Estock();
            window.open();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Open the window.
     */
    public void open() {
        createContents();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
            
        }
    }


    /**
     * Create contents of the window.
     */
    protected void createContents() {
        shell = new Shell();
        shell.setSize(550, 300);
        shell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        shell.setText("Estock");

        InputStream nycf = Estock.class.getResourceAsStream("/org/eclipse/wb/swt/nyc.jpg");
        Image nyc = new Image(null, nycf);
        shell.setImage(nyc);
        
        Label intro = new Label(shell, SWT.NONE);
        intro.setAlignment(SWT.CENTER);
        intro.setBounds(74, 56, 402, 84);
        intro.setText(
            "Welcome to Estock! Press Play to Begin!");
        Font boldFont = new Font( intro.getDisplay(), new FontData( "Arial", 12, SWT.BOLD ) );
        intro.setFont( boldFont );
        intro.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        
        
        Label news = new Label(shell, SWT.NONE);
        news.setBounds(505, 10, 450, 150);
        news.setText("");
        news.setVisible(false);

      //Game Description to the game:
        Label gameD = new Label(shell, SWT.NONE);
        gameD.setAlignment(SWT.CENTER);
        gameD.setBounds(43, 56, 402, 255);
        gameD.setText(
            "Welcome to your first day at work! Your boss has given you $10000 "
           +"to invest in various stocks for the next work week! You have to "
           +"impress you boss by making the most amount of money. Click the Hint button"
           + "to get guidance for what you should do that day. You can information about the stock and"
           +"the companies are doing by clicking their Logo. \n\nClick Day 1 to start!");
        gameD.setFont( boldFont );
        gameD.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        gameD.setVisible(false);
        
        Button btnPortfolio = new Button(shell, SWT.NONE);
        btnPortfolio.setVisible(false);
        btnPortfolio.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
               if(gameb == 1)
               {
                   news.setText("Day 1 News:\n"
                       + "The NASDAQ has been increasing steadily for the past month\nand has had "
                       + "a growth rate not seen since Feburary.\n"
                       + "Hint: Be wary of increasing stock rates for extended period. Wait a\nday to see "
                       + "what happens to the stock.");
               }
               else if(gameb ==2)
               {
                   news.setText("Day 2 News: \n\nThe treasury just had it's interest rates increase to "
                       + "over 3.25% which\nis the first time since May 2011. This causes for higher\n"
                       + "borrowing rates on houses and tech corporate debt.\n"
                       + "Hint: When the interest rates skyrocket the investors get scared.\n"
                       + "Sell certain stock based off of what is affected by the interest rate.");
               }
               else if(gameb ==3)
               {
                   news.setText("Day 3 News: \nStocks are still continuing to take a hit. Some analysts claim "
                       + "this is\njust a correction in the market. Tech stock seems to be getting hit\nthe "
                       + "hardest yet some claim now is not the time to sell\n but just to wait.\n"
                       + "Hint: Pull your stocks from the tech companies and invest in the\none non-tech company.");
               }
               else if(gameb ==4)
               {
                   news.setText("Day 4 News: \n\nThe stock market seems to be going into a serious dip.\nThere doesn't "
                       + "seems to be an end in sight for the dip in the\nmarket. The tech sector is still stuck "
                       + "in a trade war with China.\n"
                       + "Hint: Look at the trends for all of the companies for the week. \nInvest accordingly");
               }
            }
        });
        btnPortfolio.setBounds(378, 10, 85, 26);
        btnPortfolio.setText("Hint");
        btnPortfolio.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN)); 
        

     
        Button btnPlay = new Button(shell, SWT.NONE);
        btnPlay.setBounds(182, 146, 158, 77);
        btnPlay.setText("Play");
        btnPlay.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_RED)); 
        btnPlay.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                intro.dispose();
                btnPlay.setVisible(false);
                shell.setSize(500, 500);
                gameD.setVisible(true);
            }
        });
        Font boldFont2 = new Font( intro.getDisplay(), new FontData( "Arial", 16, SWT.BOLD ) );
        btnPlay.setFont(boldFont2); 
        
        Label own = new Label(shell, SWT.NONE);
        own.setBounds(18, 105, 480, 20);
        own.setText("Own:         Own:         Own:          Own:         Own:");
        own.setFont(boldFont);
        own.setVisible(false);
        own.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN)); 
        
        Label owned = new Label(shell, SWT.NONE);
        owned.setBounds(18, 125, 480, 20);
        owned.setText(""+apple+"                "+micro+"                "+capital+"                 "+google+"                "+face);
        owned.setFont(boldFont);
        owned.setVisible(false);
        owned.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        
        Label port = new Label(shell, SWT.NONE);
        port.setBounds(18, 340, 400, 45);
        port.setText("How much you Portfolio is worth: $" + totalPortfolio);
        port.setVisible(false);
        port.setFont( boldFont );
        port.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN)); 
        
        //BUYING
        Label buy = new Label(shell, SWT.NONE);
        buy.setBounds(18, 150, 480, 25);
        buy.setText("Buy:          Buy:          Buy:          Buy:           Buy:");
        buy.setFont(boldFont);
        buy.setVisible(false);
        buy.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN)); 
        

        Button applebuy = new Button(shell, SWT.NONE);
        applebuy.setBounds(18, 180, 50, 50);
        applebuy.setVisible(false);
        applebuy.setText("+");
        applebuy.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if((double)totalMoney >= appleprice[pos])
                {
                    apple++;
                    totalMoney -= appleprice[pos];
                    totalPortfolio += appleprice[pos];
                    owned.setText(""+apple+"                "+micro+"                "+capital+"                 "+google+"                "+face);
                    total.setText("Your Total Money Currently: $" + totalMoney);

                    port.setText("How much you Portfolio is worth: $" + totalPortfolio);
                    port.setVisible(true);
                    port.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
                }
            }
        });
        applebuy.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN)); 
        
        Button microbuy = new Button(shell, SWT.NONE);
        microbuy.setBounds(118, 180, 50, 50);
        microbuy.setVisible(false);
        microbuy.setText("+");
        microbuy.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if((double)totalMoney >= microprice[pos])
                {
                    micro++;
                    totalMoney -= microprice[pos];
                    totalPortfolio += microprice[pos];
                    owned.setText(""+apple+"                "+micro+"                "+capital+"                 "+google+"                "+face);
                    total.setText("Your Total Money Currently: $" + totalMoney);

                    port.setText("How much you Portfolio is worth: $" + totalPortfolio);
                    port.setVisible(true);
                    port.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
                }
            }
        });
        microbuy.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN)); 
        
        
        Button capitalbuy = new Button(shell, SWT.NONE);
        capitalbuy.setBounds(218, 180, 50, 50);
        capitalbuy.setVisible(false);
        capitalbuy.setText("+");
        capitalbuy.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if((double)totalMoney >= capitalprice[pos])
                {
                    capital++;
                    totalMoney -= capitalprice[pos];
                    totalPortfolio += capitalprice[pos];
                    owned.setText(""+apple+"                "+micro+"                "+capital+"                 "+google+"                "+face);
                    total.setText("Your Total Money Currently: $" + totalMoney);

                    port.setText("How much you Portfolio is worth: $" + totalPortfolio);
                    port.setVisible(true);
                    port.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
                }
            }
        });
        capitalbuy.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN)); 
        
        Button googlebuy = new Button(shell, SWT.NONE);
        googlebuy.setBounds(318, 180, 50, 50);
        googlebuy.setVisible(false);
        googlebuy.setText("+");
        googlebuy.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if((double)totalMoney >= googleprice[pos])
                {
                    google++;
                    totalMoney -= googleprice[pos];
                    totalPortfolio += googleprice[pos];
                    owned.setText(""+apple+"                "+micro+"                "+capital+"                 "+google+"                "+face);
                    total.setText("Your Total Money Currently: $" + totalMoney);

                    port.setText("How much you Portfolio is worth: $" + totalPortfolio);
                    port.setVisible(true);
                    port.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
                }
            }
        });
        googlebuy.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN)); 
        
        Button facebuy = new Button(shell, SWT.NONE);
        facebuy.setBounds(418, 180, 50, 50);
        facebuy.setVisible(false);
        facebuy.setText("+");
        facebuy.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if((double)totalMoney >= facebookprice[pos])
                {
                    face++;
                    totalMoney -= facebookprice[pos];
                    totalPortfolio += facebookprice[pos];
                    owned.setText(""+apple+"                "+micro+"                "+capital+"                 "+google+"                "+face);
                    total.setText("Your Total Money Currently: $" + totalMoney);

                    port.setText("How much you Portfolio is worth: $" + totalPortfolio);
                    port.setVisible(true);
                    port.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
                }
            }
        });
        facebuy.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN)); 
        
        //SELLING 
        Label sell = new Label(shell, SWT.NONE);
        sell.setBounds(18, 235, 480, 25);
        sell.setText("Sell:          Sell:           Sell:          Sell:          Sell:");
        sell.setFont(boldFont);
        sell.setVisible(false);
        sell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN)); 
        
     

        Button applesell = new Button(shell, SWT.NONE);
        applesell.setBounds(18, 265, 50, 50);
        applesell.setVisible(false);
        applesell.setText("-");
        applesell.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if(apple > 0)
                {
                    apple--;
                    totalMoney += appleprice[pos];
                    totalPortfolio -= appleprice[pos];
                    owned.setText(""+apple+"                "+micro+"                "+capital+"                 "+google+"                "+face);
                    total.setText("Your Total Money Currently: $" + totalMoney);

                    port.setText("How much you Portfolio is worth: $" + totalPortfolio);
                    port.setVisible(true);
                    port.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
                }
            }
        });
        applesell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_RED)); 
        
        Button microsell = new Button(shell, SWT.NONE);
        microsell.setBounds(118, 265, 50, 50);
        microsell.setVisible(false);
        microsell.setText("-");
        microsell.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if(micro > 0)
                {
                    micro--;
                    totalMoney += microprice[pos];
                    totalPortfolio -= microprice[pos];
                    owned.setText(""+apple+"                "+micro+"                "+capital+"                 "+google+"                "+face);
                    total.setText("Your Total Money Currently: $" + totalMoney);

                    port.setText("How much you Portfolio is worth: $" + totalPortfolio);
                    port.setVisible(true);
                    port.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
                }
            }
        });
        microsell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_RED)); 
        
        
        Button capitalsell = new Button(shell, SWT.NONE);
        capitalsell.setBounds(218, 265, 50, 50);
        capitalsell.setVisible(false);
        capitalsell.setText("-");
        capitalsell.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if(capital > 0)
                {
                    capital--;
                    totalMoney += capitalprice[pos];
                    totalPortfolio -= capitalprice[pos];
                    owned.setText(""+apple+"                "+micro+"                "+capital+"                 "+google+"                "+face);
                    total.setText("Your Total Money Currently: $" + totalMoney);

                    port.setText("How much you Portfolio is worth: $" + totalPortfolio);
                    port.setVisible(true);
                    port.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
                }
            }
        });
        capitalsell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_RED)); 
        
        Button googlesell = new Button(shell, SWT.NONE);
        googlesell.setBounds(318, 265, 50, 50);
        googlesell.setVisible(false);
        googlesell.setText("-");
        googlesell.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if(google > 0)
                {
                    google--;
                    totalMoney += googleprice[pos];
                    totalPortfolio -= googleprice[pos];
                    owned.setText(""+apple+"                "+micro+"                "+capital+"                 "+google+"                "+face);
                    total.setText("Your Total Money Currently: $" + totalMoney);

                    port.setText("How much you Portfolio is worth: $" + totalPortfolio);
                    port.setVisible(true);
                    port.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
                }
            }
        });
        googlesell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_RED)); 
        
        Button facesell = new Button(shell, SWT.NONE);
        facesell.setBounds(418, 265, 50, 50);
        facesell.setVisible(false);
        facesell.setText("-");
        facesell.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if(face > 0)
                {
                    face--;
                    totalMoney += facebookprice[pos];
                    totalPortfolio -= facebookprice[pos];
                    owned.setText(""+apple+"                "+micro+"                "+capital+"                 "+google+"                "+face);
                    total.setText("Your Total Money Currently: $" + totalMoney);

                    port.setText("How much you Portfolio is worth: $" + totalPortfolio);
                    port.setVisible(true);
                    port.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
                }
            }
        });
        facesell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_RED)); 
        
        InputStream inputa = Estock.class.getResourceAsStream("/org/eclipse/wb/swt/apple.png");
        Image apple2 = new Image(null, inputa);
        InputStream inputm = Estock.class.getResourceAsStream("/org/eclipse/wb/swt/newlogo.png");
        Image microsoft = new Image(null, inputm);
        InputStream inputc = Estock.class.getResourceAsStream("/org/eclipse/wb/swt/capital.png");
        Image capital2 = new Image(null, inputc);
        InputStream inputg = Estock.class.getResourceAsStream("/org/eclipse/wb/swt/google.png");
        Image google2 = new Image(null, inputg);
        InputStream inputf = Estock.class.getResourceAsStream("/org/eclipse/wb/swt/facebook.png");
        Image facebook = new Image(null, inputf);
        InputStream applepic = Estock.class.getResourceAsStream("/org/eclipse/wb/swt/applestock.jpg");
        Image applestock2 = new Image(null, applepic);
        InputStream microepic = Estock.class.getResourceAsStream("/org/eclipse/wb/swt/newone.jpg");
        Image microsoftstock2 = new Image(null, microepic);
        InputStream cappic = Estock.class.getResourceAsStream("/org/eclipse/wb/swt/capitalstock.jpg");
        Image capitalstock2 = new Image(null, cappic);
        InputStream googlepic = Estock.class.getResourceAsStream("/org/eclipse/wb/swt/googlestock.jpg");
        Image googlestock2 = new Image(null, googlepic);
        InputStream facebookpic = Estock.class.getResourceAsStream("/org/eclipse/wb/swt/facebookstock.jpg");
        Image facebookstock2 = new Image(null, facebookpic);

        Label lbWhichStock = new Label(shell, SWT.NONE);
        lbWhichStock.setBounds(505, 180, 500, 20);
        lbWhichStock.setText(stockname);
        Font boldFont5 = new Font( intro.getDisplay(), new FontData( "Arial", 10, SWT.BOLD ) );
        lbWhichStock.setFont( boldFont5 );
        lbWhichStock.setVisible(false);
        lbWhichStock.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN)); 
        
        Label priceStock = new Label(shell, SWT.NONE);
        priceStock.setBounds(505, 160, 500, 20);
        priceStock.setText("");
        priceStock.setFont( boldFont5 );
        priceStock.setVisible(false);
        priceStock.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN)); 
        
        Button hover = new Button(shell, SWT.NONE);
        hover.setBounds(505+minus, 198, 480-minus, 165);
        hover.setVisible(false);
        hover.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN)); 
        
        Button applestock = new Button(shell, SWT.NONE);
        applestock.setBounds((505), 200, 451, 163);
        applestock.setImage(applestock2);
        applestock.setVisible(false);
        applestock.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

            }
        });
        applestock.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN)); 
        
        Button microsoftstock = new Button(shell, SWT.NONE);
        microsoftstock.setBounds((505), 200, 480, 163);
        microsoftstock.setImage(microsoftstock2);
        microsoftstock.setVisible(false);
        microsoftstock.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

            }
        });
        microsoftstock.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN)); 
        
        Button capitalstock = new Button(shell, SWT.NONE);
        capitalstock.setBounds((505), 200, 480, 163);
        capitalstock.setImage(capitalstock2);
        capitalstock.setVisible(false);
        capitalstock.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

            }
        });
        capitalstock.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN)); 
        
        Button googlestock = new Button(shell, SWT.NONE);
        googlestock.setBounds((505), 200, 480, 163);
        googlestock.setImage(googlestock2);
        googlestock.setVisible(false);
        googlestock.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

            }
        });
        googlestock.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN)); 
        
        Button facebookstock = new Button(shell, SWT.NONE);
        facebookstock.setBounds((505), 200, 480, 163);
        facebookstock.setImage(facebookstock2);
        facebookstock.setVisible(false);
        facebookstock.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

            }
        });
        facebookstock.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN)); 
     
        Button applebutton = new Button(shell, SWT.NONE);
        applebutton.setBounds(18, 56, 50, 50);
        applebutton.setImage(apple2);
        applebutton.setVisible(false);
        applebutton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.setSize(1000, 500);
                facebookstock.setVisible(false);
                microsoftstock.setVisible(false);
                googlestock.setVisible(false);
                capitalstock.setVisible(false);
                hover.setVisible(true);
                applestock.setVisible(true);
                stockname = "Market Summary > Apple, Inc. Common Stock";
                lbWhichStock.setText(stockname);
                lbWhichStock.setVisible(true);
                priceStock.setText("Price of Apple Stock Currently: $"+appleprice[pos]);
                priceStock.setVisible(true);
                news.setVisible(true);
            }
        });
        applebutton.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN)); 
       
        Button microsoftbutton = new Button(shell, SWT.NONE);
        microsoftbutton.setBounds(77, 56, 130, 50);
        microsoftbutton.setImage(microsoft);
        microsoftbutton.setVisible(false);
        microsoftbutton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.setSize(1000, 500);
                facebookstock.setVisible(false);
                googlestock.setVisible(false);
                capitalstock.setVisible(false);
                applestock.setVisible(false);
                hover.setVisible(true);
                microsoftstock.setVisible(true);
                stockname = "Market Summary > Youngevity, Inc. Common Stock";
                lbWhichStock.setText(stockname);
                lbWhichStock.setVisible(true);
                priceStock.setText("Price of Youngevity Stock Currently: $"+microprice[pos]);
                priceStock.setVisible(true);
                news.setVisible(true);
            }
        });
        microsoftbutton.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE)); 
        
        Button capitalbutton = new Button(shell, SWT.NONE);
        capitalbutton.setBounds(218, 56, 50, 50);
        capitalbutton.setImage(capital2);
        capitalbutton.setVisible(false);
        capitalbutton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.setSize(1000, 500);
                facebookstock.setVisible(false);
                googlestock.setVisible(false);
                microsoftstock.setVisible(false);
                applestock.setVisible(false);
                hover.setVisible(true);
                capitalstock.setVisible(true);
                stockname = "Market Summary > Captial One, Inc. Common Stock";
                lbWhichStock.setText(stockname);
                lbWhichStock.setVisible(true);
                priceStock.setText("Price of Capital One Stock Currently: $"+capitalprice[pos]);
                priceStock.setVisible(true);
                news.setVisible(true);
            }
        });
        capitalbutton.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN)); 
        
        Button googlebutton = new Button(shell, SWT.NONE);
        googlebutton.setBounds(318, 56, 50, 50);
        googlebutton.setImage(google2);
        googlebutton.setVisible(false);
        googlebutton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.setSize(1000, 500);
                facebookstock.setVisible(false);
                capitalstock.setVisible(false);
                microsoftstock.setVisible(false);
                applestock.setVisible(false);
                hover.setVisible(true);
                googlestock.setVisible(true);
                stockname = "Market Summary > Alphabet, Inc. Common Stock";
                lbWhichStock.setText(stockname);
                lbWhichStock.setVisible(true);
                priceStock.setText("Price of Alphabet Stock Currently: $"+googleprice[pos]);
                priceStock.setVisible(true);
                news.setVisible(true);
            }
        });
        googlebutton.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN)); 
        
        Button facebookbutton = new Button(shell, SWT.NONE);
        facebookbutton.setBounds(418, 56, 50, 50);
        facebookbutton.setImage(facebook);
        facebookbutton.setVisible(false);
        facebookbutton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.setSize(1000, 500);
                googlestock.setVisible(false);
                capitalstock.setVisible(false);
                microsoftstock.setVisible(false);
                applestock.setVisible(false);
                hover.setVisible(true);
                facebookstock.setVisible(true);
                stockname = "Market Summary > Facebook, Inc. Common Stock";
                lbWhichStock.setText(stockname);
                lbWhichStock.setVisible(true);
                priceStock.setText("Price of Facebook Stock Currently: $"+facebookprice[pos]);
                priceStock.setVisible(true);
                news.setVisible(true);
            }
        });
        facebookbutton.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN)); 
        
        
        //Total amount
        total = new Label(shell, SWT.BORDER);
        total.setBounds(18, 10, 332, 40);
        total.setText("Your Total Money Currently: $" + totalMoney);
        total.setVisible(false);
        Font boldFont3 = new Font( intro.getDisplay(), new FontData( "Arial", 11, SWT.BOLD) );
        total.setFont(boldFont3);
        total.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN)); 

        
        Label analysisresults = new Label(shell, SWT.NONE);
        analysisresults.setBounds(500, 170, 500, 400);
        analysisresults.setText(day1[0] + ":\n" + day1[day1w] + "\n" + day2[0] + ":\n" + day2[day2w] + "\n" +day3[0] + ":\n" + day3[day3w] + "\n" + day4[0] + ":\n" + day4[day4w]);
        analysisresults.setFont( boldFont5 );
        analysisresults.setVisible(false);
        analysisresults.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN)); 
        
        Button analysis = new Button(shell, SWT.NONE);
        analysis.setBounds(655, 100, 150, 50);
        analysis.setVisible(false);
        analysis.setText("Analysis");
        analysis.moveAbove(news);
        analysis.setFont(boldFont3);
        analysis.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                facebookstock.setVisible(false);
                capitalstock.setVisible(false);
                microsoftstock.setVisible(false);
                applestock.setVisible(false);
                hover.setVisible(false);
                googlestock.setVisible(false);
                lbWhichStock.setVisible(false);
                priceStock.setVisible(false);
                analysisresults.setText(day1[0] + ":\n" + day1[day1w] + "\n" + day2[0] + ":\n" + day2[day2w] + "\n" +day3[0] + ":\n" + day3[day3w] + "\n" + day4[0] + ":\n" + day4[day4w]);
                analysisresults.setVisible(true);
            }
        });
        analysis.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_BLUE));
        
        Button btnDay = new Button(shell, SWT.NONE);
        btnDay.setBounds(170, 400, 130, 50);
        btnDay.setText("Day 1");
        btnDay.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_BLUE));
        btnDay.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
               if(gameb == 0)
               {
               gameD.dispose();
               btnDay.setText("Day 1");
               applebutton.setVisible(true);
               capitalbutton.setVisible(true);
               facebookbutton.setVisible(true);
               googlebutton.setVisible(true);
               microsoftbutton.setVisible(true);
               total.setVisible(true);
               gameb = gameb + 1; 
               own.setVisible(true);
               owned.setVisible(true);
               buy.setVisible(true);
               sell.setVisible(true);
               applebuy.setVisible(true);
               microbuy.setVisible(true);
               capitalbuy.setVisible(true);
               googlebuy.setVisible(true);
               facebuy.setVisible(true);
               applesell.setVisible(true);
               microsell.setVisible(true);
               capitalsell.setVisible(true);
               googlesell.setVisible(true);
               facesell.setVisible(true);
               btnPortfolio.setVisible(true);
               news.setText("Day 1 News: \n\n"
                   + "The NASDAQ has been increasing steadily for the past month\nand has had "
                   + "a growth rate not seen since Feburary.");
               news.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN)); 
               port.setText("How much you Portfolio is worth: $" + totalPortfolio);
               port.setVisible(true);
               port.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN)); 
                  
               }
               else if(gameb == 1)
               {
                   priceStock.setVisible(false);
                   btnDay.setText("Day 2");
                   minus = minus + 92;
                   hover.setBounds(505+minus, 200, 485-minus, 163);
                   gameb = gameb + 1;  
                   pos++;
                   news.setText("Day 2 News: \n\nThe treasury just had it's interest rates increase to"
                       + " over 3.25% which\nis the first time since May 2011. This causes for higher\n"
                       + "borrowing rates on houses and tech corporate debt.");
                   news.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN)); 
                   ap = (int)(apple*appleprice[pos]);
                   mp = (int)(micro*microprice[pos]);
                   cp = (int)(capital*capitalprice[pos]);
                   gp = (int)(google*googleprice[pos]);
                   fp = (int)(face*facebookprice[pos]);
                   totalPortfolio = ap + mp + cp + gp + fp; 
                   port.setText("How much you Portfolio is worth: $" + totalPortfolio);
                   port.setVisible(true);
                   port.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
                   if(totalMoney > 9000)
                   {
                      day1w = 1; 
                   }
                   else if(totalMoney > 5000)
                   {
                       day1w = 2;  
                   }
                   else if(totalMoney < 5000)
                   {
                       day1w = 3; 
                   }
                  
               }
               else if(gameb == 2)
               {
                   priceStock.setVisible(false);
                   btnDay.setText("Day 3");
                   minus = minus + 92;
                   hover.setBounds(505+minus, 200, 485-minus, 163);
                   gameb = gameb + 1; 
                   pos++;
                   ap = (int)(apple*appleprice[pos]);
                   mp = (int)(micro*microprice[pos]);
                   cp = (int)(capital*capitalprice[pos]);
                   gp = (int)(google*googleprice[pos]);
                   fp = (int)(face*facebookprice[pos]);
                   totalPortfolio = ap + mp + cp + gp + fp; 
                   news.setText("Day 3 News: \n\nStocks are still continuing to take a hit. Some analysts claim "
                       + "this is\njust a correction in the market. Tech stock seems to be getting hit\nthe "
                       + "hardest yet some claim now is not the time to sell\n but just to wait.");
                   news.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
                   port.setText("How much you Portfolio is worth: $" + totalPortfolio);
                   port.setVisible(true);
                   port.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
                   if(apple < 4 && capital < 4 && face < 4 && google < 4)
                   {
                       day2w = 1;
                   }
                   else
                   {
                       day2w = 2; 
                   }
                   
               }
               else if(gameb == 3)
               {
                   news.setText("Day 4 News: \n\nThe stock market seems to be going into a serious dip.\nThere doesn't "
                       + "seems to be an end in sight for the dip in the\nmarket. The tech sector is still stuck "
                       + "in a trade war with China.");
                   news.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
                   priceStock.setVisible(false);
                   btnDay.setText("Day 4");
                   minus = minus + 92;
                   hover.setBounds(505+minus, 200, 485-minus, 163);
                   gameb = gameb + 1; 
                   pos++;
                   ap = (int)(apple*appleprice[pos]);
                   mp = (int)(micro*microprice[pos]);
                   cp = (int)(capital*capitalprice[pos]);
                   gp = (int)(google*googleprice[pos]);
                   fp = (int)(face*facebookprice[pos]);
                   totalPortfolio = ap + mp + cp + gp + fp; 
                   port.setText("How much you Portfolio is worth: $" + totalPortfolio);
                   port.setVisible(true);
                   port.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
                   if(apple < 2 && capital < 2 && face < 2 && google < 2)
                   {
                       day3w = 1;
                   }
                   else
                   {
                       day3w = 2; 
                   }
               }
               else if(gameb == 4)
               {
                   news.setText("\nCongradulations You have finished the week!\nPress Analysis to see your report.");
                   news.setFont(boldFont);
                   priceStock.setVisible(false);
                   btnDay.setText("Done!");
                   minus = minus + 92;
                   hover.setBounds(505+minus, 200, 485-minus, 163);
                   gameb = gameb + 1;    
                   ap = (int)(apple*appleprice[pos]);
                   mp = (int)(micro*microprice[pos]);
                   cp = (int)(capital*capitalprice[pos]);
                   gp = (int)(google*googleprice[pos]);
                   fp = (int)(face*facebookprice[pos]);
                   totalPortfolio = ap + mp + cp + gp + fp; 
                   port.setText("Portfolio is worth: $" + totalPortfolio + "\n" + "Liquidated assests are: $" + (totalPortfolio+totalMoney));
                   port.setVisible(true);
                   port.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));

                   analysis.setVisible(true);
                   if(micro > 6)
                   {
                       day4w = 1;
                   }
                   else
                   {
                       day4w = 2; 
                   }
               }
            }
        });
        Font boldFont4 = new Font( intro.getDisplay(), new FontData( "Arial", 12, SWT.BOLD ) );
        btnDay.setFont(boldFont4);
        
        
        
      
    }
}
