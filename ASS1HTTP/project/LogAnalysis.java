
 /*
  * Group- ELITE FOUR!
 * Problem Statement-Representation of http log file (weblog.txt)
  */

package httplogfile;
import java.io.*;
import java.util.*;

public class LogAnalysis extends javax.swing.JFrame {
    
        String name;
        String lineX;
        String[] ip;
        String[] date;
        String[] page;//for storing the pages
        String[] err;
        String[] da;
        String[] brow;
        int google=0,msn1=0,cul=0,exa=0,msn=0,jeev=0,yahoo=0;
        ArrayList<String> ar=new ArrayList<String>();//ip's
        ArrayList<String> website=new ArrayList<String>();//pages and website
        ArrayList<String> pages =new ArrayList<String>();//pages
        ArrayList<String> domain =new ArrayList<String>();//domain
        ArrayList<String> error=new ArrayList<String>();//errors
        ArrayList<String> refere=new ArrayList<String>();//referer
        ArrayList<String> user_agent=new ArrayList<String>();//user_agents 
        ArrayList<String> images=new ArrayList<String>();
        ArrayList<String> dates=new ArrayList<String>();
        ArrayList<String> browser=new ArrayList<String>();
        Set<String> uniquebrowser;
        Set<String> uniqueip;
        Set<String> virtual_domains;
        Set<String> uniquepages;
        Set<String> uniqueimg;
        Set<String> uniqueref;
        int three=0,four=0,five=0;
        HashMap hm=new HashMap();
        HashMap ip_dates=new HashMap();
        HashMap top_refere=new HashMap();
        HashMap spider_count=new HashMap();
        HashMap browser_count=new HashMap();
        int lineno=0;
        
    public LogAnalysis() {
        initComponents();
    }
     public LogAnalysis(String file_name)throws IOException {
         name=file_name;
          initComponents();
          BufferedReader br=new BufferedReader(new FileReader(name));
        
       
        while((lineX=br.readLine())!=null)
        {
         
           ip=lineX.split("-",2);//separating ip's and rest of the string
            //System.out.println(ip[0]);
           ar.add(ip[0]);
           ip[1].trim();
           
           date=ip[1].split(" ",7);//consist date and rest part of the line        
           da=date[2].split(":",2);
           dates.add(da[1].substring(3,5));
           System.out.println(da[1].substring(3, 5));
           website.add(date[5]);
           page=date[5].split("/",2);//separating websites and pages of it
           pages.add(page[1]);
           domain.add(page[0]);
           //System.out.println(page[1]);
           err=date[6].split(" ",5);
           //System.out.println(err[3]);
           error.add(err[1]);
           user_agent.add(err[4]);
           refere.add(err[3]);
           brow=err[4].split("/",2);
           browser.add(brow[0].substring(1));
           lineno++;
        }
        int win=0;
        for(String s:user_agent)
        {
            if(s.matches("(.*)Windows NT 5.1(.*)"))
                win++;
        }
        for(String s :pages)
        {
            if(s.matches("(.*)png"))
                images.add(s);
            if(s.matches("(.*)jpg"))
                images.add(s);
            if(s.matches("(.*).gif"))
                images.add(s);      
        }
        int count;
        
        //for(String s :images)
           // System.out.println(s);
        uniqueimg=new HashSet<String>(images);
        for(String s: uniqueimg)
        {
                count=1;
                
                  count=Collections.frequency(images,s);
                    hm.put(s,count);
                
        }
         Set set = hm.entrySet();
      // Get an iterator
      Iterator i = set.iterator();
      // Display elements
      while(i.hasNext()) {
         Map.Entry me = (Map.Entry)i.next();
         System.out.print(me.getKey() + ": ");
         System.out.println(me.getValue());
          }
     uniqueref=new HashSet<String>(refere);
     uniquepages=new HashSet<String>(pages);
     virtual_domains=new HashSet<String>(domain);
     uniquebrowser=new HashSet<String>(browser);
     for(String s: uniquebrowser)
        {
                count=1;
                
                  count=Collections.frequency(browser,s);
                    browser_count.put(s,count);          
        }
     for(String s: virtual_domains)
        {
                count=1;
                
                  count=Collections.frequency(domain,s);
                    top_refere.put(s,count);
                
        }
        System.out.println("Windows NT users="+win);
        System.out.println("ALL IP="+ar.size());
         uniqueip=new HashSet<String>(ar);//Unique IP's
        System.out.println("Unique IP="+uniqueip.size());
        //Unique Pages
        System.out.println("ALL Pages="+pages.size());
        
        System.out.println("Unique Pages="+uniquepages.size());   
                //Virtual Domain

        System.out.println("Virtual domain");
     for(String s: virtual_domains)
     {
            System.out.println(s);
     }
        System.out.println("total number of lines="+lineno);
        System.out.println("Unique IP count: " + uniqueip.size());
    
        //counting the errors 4*,5*,3*
        
        for(String s :error)
        {
            if(s.matches("(4).*"))
                four++;
            if(s.matches("(3).*"))
                three++;
            if(s.matches("(5).*"))
                five++;
        }
        //fining the unique referre
        
        
        System.out.println("Page Not found Error="+four);
        System.out.println("Internal Server Error="+five);
        System.out.println("Redirect="+three);
       //cal_visitors();
        jLabel1.setText(name);
        jLabel8.setText(String.valueOf(uniqueip.size()));
        jLabel9.setText(String.valueOf(four+five));
        jLabel10.setText(String.valueOf(lineno));
        jLabel11.setText(String.valueOf(virtual_domains.size()));
        jLabel17.setText(String.valueOf(uniquepages.size()));
        jLabel15.setText(String.valueOf(uniqueref.size()));
        
        jLabel19.setText(String.valueOf(uniquebrowser.size()));
    }
   

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Deatiled Anlysis");
        setMinimumSize(new java.awt.Dimension(775, 530));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("path");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(196, 98, 550, 17);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Unique IP's :");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(28, 169, 97, 17);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Errors :");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(28, 203, 97, 17);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Virtual Domain :");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(28, 268, 110, 17);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Total Hits :");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(28, 133, 97, 17);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Images :");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel6);
        jLabel6.setBounds(28, 331, 110, 17);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Unique Pages :");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(30, 300, 110, 17);

        jButton1.setText("Details");
        jButton1.setName("UIP"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(341, 168, 90, 23);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(8);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(460, 120, 290, 180);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setText("2");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(200, 170, 60, 16);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel9.setText("1");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(200, 200, 60, 20);

        jButton2.setText("Details");
        jButton2.setName("err"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(341, 202, 90, 23);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel10.setText("3");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(200, 130, 90, 20);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel11.setText("7");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(200, 270, 70, 16);

        jButton3.setText("Details");
        jButton3.setName("vd"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(341, 267, 90, 23);

        jButton4.setText("Details");
        jButton4.setName("upage"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(341, 301, 90, 23);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        getContentPane().add(jLabel12);
        jLabel12.setBounds(0, 0, 0, 0);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Referring sites :");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(28, 365, 108, 17);

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(8);
        jScrollPane2.setViewportView(jTextArea2);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(460, 330, 290, 150);

        jLabel14.setText("COUNT !");
        getContentPane().add(jLabel14);
        jLabel14.setBounds(460, 310, 60, 14);

        jButton5.setText("Details");
        jButton5.setName("ref"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(341, 364, 90, 23);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel15.setText("4");
        getContentPane().add(jLabel15);
        jLabel15.setBounds(200, 360, 70, 20);

        jButton6.setText("Details");
        jButton6.setName("img"); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6);
        jButton6.setBounds(341, 330, 90, 23);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Spider :");
        getContentPane().add(jLabel16);
        jLabel16.setBounds(28, 399, 53, 17);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("6");
        getContentPane().add(jLabel17);
        jLabel17.setBounds(200, 300, 60, 20);

        jButton7.setText("Details");
        jButton7.setName("spider"); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7);
        jButton7.setBounds(341, 398, 90, 23);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Browser :");
        getContentPane().add(jLabel18);
        jLabel18.setBounds(28, 234, 97, 17);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel19.setText("5");
        getContentPane().add(jLabel19);
        jLabel19.setBounds(200, 230, 50, 20);

        jButton8.setText("Details");
        jButton8.setName("brw"); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8);
        jButton8.setBounds(341, 233, 90, 23);

        jLabel20.setFont(new java.awt.Font("Monotype Corsiva", 1, 28)); // NOI18N
        jLabel20.setText("Detailed Analysis");
        getContentPane().add(jLabel20);
        jLabel20.setBounds(220, 20, 290, 49);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Log File Path :");
        getContentPane().add(jLabel21);
        jLabel21.setBounds(28, 96, 97, 17);

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/httplogfile/back.JPG"))); // NOI18N
        jLabel22.setText("jLabel2");
        getContentPane().add(jLabel22);
        jLabel22.setBounds(-250, -290, 1040, 800);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jTextArea1.setText("");
        for(String s :uniqueip){
                  jTextArea1.append(s+"\n");
              }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        jTextArea1.setText("");
        jTextArea2.setText("");
        int[] info=new int[3];
        jTextArea1.setText("");
        jTextArea1.append("Page Not found Error="+four+"\n");
        jTextArea1.append("Internal Server Error="+five+"\n");
        jTextArea1.append("Redirect="+three+"\n");
        info[0]=four;info[1]=five;info[2]=three;
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jTextArea2.setVisible(false);
        jTextArea1.setText("");
        jTextArea2.setText("");
        for(String s: virtual_domains)
        {
            jTextArea1.append(s+"\n");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        jTextArea1.setText("");
        jTextArea2.setText("");
        for(String s :uniquepages)
        {
            jTextArea1.append(s+"\n");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jTextArea2.setVisible(true);        // TODO add your handling code here:
        jLabel15.setText(String.valueOf(virtual_domains.size()));
        jTextArea1.setText("");
        jTextArea2.setText("");
        for(String s :virtual_domains)
        {
            jTextArea1.append(s+"\n");
            
        }
         Set set = top_refere.entrySet();
      // Get an iterator
     Iterator i = set.iterator();
      // Display elements
      while(i.hasNext()) 
      {
         Map.Entry me = (Map.Entry)i.next();
         System.out.print(me.getKey() + ": ");
         System.out.println(me.getValue());
         jTextArea2.append(me.getKey()+" "+"=");
         jTextArea2.append(me.getValue()+"\n");
      }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        jTextArea1.setText("");
        jTextArea2.setText("");
        for(String s :uniqueimg)
        {
            jTextArea1.append(s+"\n");
        }
        Set set = hm.entrySet();
      // Get an iterator
     Iterator i = set.iterator();
      // Display elements
      while(i.hasNext()) {
         Map.Entry me = (Map.Entry)i.next();
         System.out.print(me.getKey() + ": ");
         System.out.println(me.getValue());
         jTextArea2.append(me.getKey()+" ");
         jTextArea2.append(me.getValue()+"\n");
         
          }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        jTextArea1.setText("");
        jTextArea2.setText("");
        for(String s:user_agent)
        {
            if(s.matches("(.*)(Googlebot)(.*)"))
                google++;
            if(s.matches("(.*)(Yahoo! Slurp)(.*)"))
                yahoo++;
          if(s.matches("(.*)(http://search.msn.com/msnbot.htm)(.*)"))
                msn++;
         if(s.matches("(.*)(exabot)(.*)"))
             exa++;
         if(s.matches("(.*)(Ask	Jeeves)(.*)"))
              jeev++;
        }
        spider_count.put("Googlebot",google);
        spider_count.put("Yahoo! Slurp",yahoo);
        spider_count.put("MSN bot", msn);
        spider_count.put("Exabot",exa);
        spider_count.put("AskJeevesRobot",jeev);
        int total=google+jeev+msn+yahoo+exa;
            Set set = spider_count.entrySet();
      // Get an iterator
     Iterator i1 = set.iterator();
      // Display elements
      while(i1.hasNext()) {
         Map.Entry me = (Map.Entry)i1.next();
        jTextArea1.append(me.getKey()+"\n");
         jTextArea2.append(me.getKey()+" ");
         jTextArea2.append(me.getValue()+"\n");            
        }
      jLabel17.setText(String.valueOf(total));
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
      jTextArea1.setText("");
        jTextArea2.setText("");
        Set set = browser_count.entrySet();
      // Get an iterator
     Iterator i1 = set.iterator();
      // Display elements
      while(i1.hasNext()) {
         Map.Entry me = (Map.Entry)i1.next();
       jTextArea1.append(me.getKey()+"\n");
         jTextArea2.append(me.getKey()+" ");
         jTextArea2.append(me.getValue()+"\n");            
        }
    }//GEN-LAST:event_jButton8ActionPerformed
    
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new LogAnalysis().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
