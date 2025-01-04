(ns project.gui
  (:import (java.awt Color FlowLayout)
           (java.awt.event MouseAdapter ActionListener)
           (java.io File)
           (java.util Locale)
           (javax.imageio ImageIO)
           (javax.swing JComboBox JOptionPane JPasswordField JScrollPane JTable JFrame JLabel JTextField JButton)
           (javax.swing.table DefaultTableModel)
           (org.apache.pdfbox.pdmodel PDDocument PDPage PDPageContentStream)
           (org.apache.pdfbox.pdmodel.common PDRectangle)
           (org.apache.pdfbox.pdmodel.font PDType0Font))
  (:require [clojure.java.io :as io]
            [project.core :as proj]
            [project.base :as db]))
(Locale/setDefault Locale/US)

(let [main-frame (new JFrame "Aircraft Performance")
      main-nmm-button (new JButton "NMM")
      main-tmm-button (new JButton "TMM")
      login-frame (new JFrame "Login")
      username-label (new JLabel "Username:")
      password-label (new JLabel "Password:")
      username-text (new JTextField)
      password-text (new JPasswordField)
      login-button (new JButton "Login")
      accounts-frame (new JFrame "Accounts Management")
      username-acc-text (new JTextField 15)
      password-acc-text (new JTextField 15)
      account-types (into-array ["Admin" "TMM" "NMM" "Director"])
      account-type-combo (new JComboBox account-types)
      table-model (new DefaultTableModel (into-array ["Username" "Password" "Account Type"]) 0)
      user-table (new JTable table-model)
      add-button (new JButton "Add User")
      delete-button (new JButton "Delete User")
      update-button (new JButton "Update User")
      scroll-pane (new JScrollPane user-table)
      nmm-frame (new JFrame "NMM")
      par1-label (new JLabel "Cpv")
      par2-label (new JLabel "Cpps")
      par3-label (new JLabel "kv")
      par4-label (new JLabel "kps")
      par6-label (new JLabel "v0")
      par7-label (new JLabel "Au")
      par8-label (new JLabel "H")
      par9-label (new JLabel "a0")
      par10-label (new JLabel "M0")
      par11-label (new JLabel "\u03C3u")
      par12-label (new JLabel "\u03B7k")
      par13-label (new JLabel "σp")
      par14-label (new JLabel "σg")
      par15-label (new JLabel "ηt")
      par16-label (new JLabel "ηm")
      par17-label (new JLabel "\u03C6")
      par18-label (new JLabel "Hd")
      par19-label (new JLabel "Rv")
      par20-label (new JLabel "Rps")
      par21-label (new JLabel "\u03c0krit")
      par22-label (new JLabel "Cz/Cx")
      par23-label (new JLabel "m")
      par24-label (new JLabel "g")
      par25-label (new JLabel "T0")
      par26-label (new JLabel "ρ0")
      par27-label (new JLabel "p0")
      exp1-label (new JLabel "kv/(kv-1)")
      exp2-label (new JLabel "kps/(kps-1)")
      exp3-label (new JLabel "(kv-1)/kv")
      exp4-label (new JLabel "1/(kps-1)")
      exp5-label (new JLabel "(kps-1)/kps")
      exp6-label (new JLabel "(kv-1)/2")
      expression-label (new JLabel "Expressions")
      inlet-label (new JLabel "Inlet")
      inlet-label1 (new JLabel "p0*")
      inlet-label2 (new JLabel "p1(real)*")
      inlet-label3 (new JLabel "T1*")
      inlet-label4 (new JLabel "\u03C0u")
      inlet-label5 (new JLabel "\u03C4u")
      chamber-label (new JLabel "Combustion chamber")
      chamber-label1 (new JLabel "p2*")
      chamber-label3 (new JLabel "T2*")
      jet-label (new JLabel "Jet")
      jet-label1 (new JLabel "πmr")
      jet-label2 (new JLabel "πm")
      jet-label3 (new JLabel "Vi")
      q-label (new JLabel "Parameters of the quadratic equation")
      a-label (new JLabel "a")
      b-label (new JLabel "b")
      c-label (new JLabel "c")
      other-label (new JLabel "Other parameters")
      g-label (new JLabel "G")
      f-label (new JLabel "F")
      fsp-label (new JLabel "Fsp")
      q-label2 (new JLabel "q")
      theta-label4 (new JLabel "\u03B8")
      mg-label (new JLabel "mg")
      csp-label (new JLabel "Csp")
      mv-label (new JLabel "mv")
      c1-label (new JLabel "C1")
      par1-text (new JTextField)
      par2-text (new JTextField)
      par3-text (new JTextField)
      par4-text (new JTextField)
      par6-text (new JTextField)
      par7-text (new JTextField)
      par8-text (new JTextField)
      par9-text (new JTextField)
      par10-text (new JTextField)
      par11-text (new JTextField)
      par12-text (new JTextField)
      par13-text (new JTextField)
      par14-text (new JTextField)
      par15-text (new JTextField)
      par16-text (new JTextField)
      par17-text (new JTextField)
      par18-text (new JTextField)
      par19-text (new JTextField)
      par20-text (new JTextField)
      par21-text (new JTextField)
      par22-text (new JTextField)
      par23-text (new JTextField)
      par24-text (new JTextField)
      par25-text (new JTextField)
      par26-text (new JTextField)
      par27-text (new JTextField)
      exp1-text (new JTextField)
      exp2-text (new JTextField)
      exp3-text (new JTextField)
      exp4-text (new JTextField)
      exp5-text (new JTextField)
      exp6-text (new JTextField)
      inlet-text1 (new JTextField)
      inlet-text2 (new JTextField)
      inlet-text3 (new JTextField)
      inlet-text4 (new JTextField)
      inlet-text5 (new JTextField)
      chamber-text1 (new JTextField)
      q-text2 (new JTextField)
      chamber-text3 (new JTextField)
      theta-text (new JTextField)
      jet-text1 (new JTextField)
      jet-text2 (new JTextField)
      jet-text3 (new JTextField)
      a-text (new JTextField)
      b-text (new JTextField)
      c-text (new JTextField)
      g-text (new JTextField)
      f-text (new JTextField)
      fsp-text (new JTextField)
      mg-text (new JTextField)
      csp-text1 (new JTextField)
      csp-text2 (new JTextField)
      mv-text (new JTextField)
      c1-text (new JTextField)
      calc-nmm-button (new JButton "Calculate")
      check-btn (new JButton "Check")
      reset-btn (new JButton "Reset")
      nmm-btn (new JButton "TMM")
      tmm-frame (new JFrame "TMM")
      export-nmm (new JButton "Export")
      tmm-par1-label (new JLabel "Cpv")
      tmm-par2-label (new JLabel "Cpps")
      tmm-par3-label (new JLabel "kv")
      tmm-par4-label (new JLabel "kps")
      tmm-par5-label (new JLabel "v0")
      tmm-par6-label (new JLabel "mv")
      tmm-par7-label (new JLabel "H")
      tmm-par8-label (new JLabel "a0")
      tmm-par9-label (new JLabel "M0")
      tmm-par10-label (new JLabel "\u03C3u")
      tmm-par11-label (new JLabel "\u03B7k")
      tmm-par12-label (new JLabel "σp")
      tmm-par13-label (new JLabel "σg")
      tmm-par14-label (new JLabel "ηt")
      tmm-par15-label (new JLabel "ηm")
      tmm-par16-label (new JLabel "\u03C6")
      tmm-par17-label (new JLabel "Hd")
      tmm-par18-label (new JLabel "Rv")
      tmm-par19-label (new JLabel "Rps")
      tmm-par20-label (new JLabel "\u03c0krit")
      tmm-par24-label (new JLabel "T0")
      tmm-par25-label (new JLabel "ρ0")
      tmm-par26-label (new JLabel "p0")
      tmm-par27-label (new JLabel "πk")
      tmm-par28-label (new JLabel "\u03b4")
      tmm-par29-label (new JLabel "T3")
      tmm-expression-label (new JLabel "Expressions")
      tmm-exp1-label (new JLabel "kv/(kv-1)")
      tmm-exp2-label (new JLabel "kps/(kps-1)")
      tmm-exp3-label (new JLabel "(kv-1)/kv")
      tmm-exp4-label (new JLabel "1/(kps-1)")
      tmm-exp5-label (new JLabel "(kps-1)/kps")
      tmm-exp6-label (new JLabel "(kv-1)/2")
      tmm-inlet-label (new JLabel "Inlet")
      tmm-inlet-label1 (new JLabel "p0*")
      tmm-inlet-label2 (new JLabel "p1(real)*")
      tmm-inlet-label3 (new JLabel "T1*")
      tmm-inlet-label4 (new JLabel "\u03C0u")
      tmm-inlet-label5 (new JLabel "\u03C4u")
      tmm-compressor-label (new JLabel "Compressor")
      tmm-compressor-label1 (new JLabel "T2*")
      tmm-compressor-label2 (new JLabel "p2")
      tmm-compressor-label3 (new JLabel "τk")
      tmm-compressor-label4 (new JLabel "τΣ")
      tmm-compressor-label5 (new JLabel "πΣ")
      tmm-chamber-label (new JLabel "Combustion chamber")
      tmm-chamber-label1 (new JLabel "p3*")
      tmm-chamber-label2 (new JLabel "q")
      tmm-chamber-label3 (new JLabel "θ")
      tmm-turbine-label (new JLabel "Turbine")
      tmm-turbine-label1 (new JLabel "πt")
      tmm-turbine-label2 (new JLabel "p4*")
      tmm-turbine-label3 (new JLabel "T4*")
      tmm-turbine-label4 (new JLabel "τt")
      tmm-turbine-label5 (new JLabel "wk")
      tmm-turbine-label6 (new JLabel "wt")
      tmm-jet-label (new JLabel "Jet")
      tmm-jet-label1 (new JLabel "πmr")
      tmm-jet-label2 (new JLabel "πm")
      tmm-jet-label3 (new JLabel "Vi")
      tmm-jet-label4 (new JLabel "mps")
      tmm-jet-label5 (new JLabel "p5")
      tmm-jet-label6 (new JLabel "f(M)")
      tmm-jet-label7 (new JLabel "Aiz")
      tmm-other-label (new JLabel "Other parameters")
      tmm-other-label1 (new JLabel "F")
      tmm-other-label2 (new JLabel "Fsp")
      tmm-other-label3 (new JLabel "Csp")
      tmm-par1-text (new JTextField)
      tmm-par2-text (new JTextField)
      tmm-par3-text (new JTextField)
      tmm-par4-text (new JTextField)
      tmm-par5-text (new JTextField)
      tmm-par6-text (new JTextField)
      tmm-par7-text (new JTextField)
      tmm-par8-text (new JTextField)
      tmm-par9-text (new JTextField)
      tmm-par10-text (new JTextField)
      tmm-par11-text (new JTextField)
      tmm-par12-text (new JTextField)
      tmm-par13-text (new JTextField)
      tmm-par14-text (new JTextField)
      tmm-par15-text (new JTextField)
      tmm-par16-text (new JTextField)
      tmm-par17-text (new JTextField)
      tmm-par18-text (new JTextField)
      tmm-par19-text (new JTextField)
      tmm-par20-text (new JTextField)
      tmm-par24-text (new JTextField)
      tmm-par25-text (new JTextField)
      tmm-par26-text (new JTextField)
      tmm-par27-text (new JTextField)
      tmm-par28-text (new JTextField)
      tmm-par29-text (new JTextField)
      tmm-exp1-text (new JTextField)
      tmm-exp2-text (new JTextField)
      tmm-exp3-text (new JTextField)
      tmm-exp4-text (new JTextField)
      tmm-exp5-text (new JTextField)
      tmm-exp6-text (new JTextField)
      tmm-inlet-text1 (new JTextField)
      tmm-inlet-text2 (new JTextField)
      tmm-inlet-text3 (new JTextField)
      tmm-inlet-text4 (new JTextField)
      tmm-inlet-text5 (new JTextField)
      tmm-compressor-text1 (new JTextField)
      tmm-compressor-text2 (new JTextField)
      tmm-compressor-text3 (new JTextField)
      tmm-compressor-text4 (new JTextField)
      tmm-compressor-text5 (new JTextField)
      tmm-chamber-text1 (new JTextField)
      tmm-chamber-text2 (new JTextField)
      tmm-chamber-text3 (new JTextField)
      tmm-turbine-text1 (new JTextField)
      tmm-turbine-text2 (new JTextField)
      tmm-turbine-text3 (new JTextField)
      tmm-turbine-text4 (new JTextField)
      tmm-turbine-text5 (new JTextField)
      tmm-turbine-text6 (new JTextField)
      tmm-jet-text1 (new JTextField)
      tmm-jet-text2 (new JTextField)
      tmm-jet-text3 (new JTextField)
      tmm-jet-text4 (new JTextField)
      tmm-jet-text5 (new JTextField)
      tmm-jet-text6 (new JTextField)
      tmm-jet-text7 (new JTextField)
      tmm-other-text1 (new JTextField)
      tmm-other-text2 (new JTextField)
      tmm-other-text3 (new JTextField)
      tmm-other-text4 (new JTextField)
      calc-tmm-button (new JButton "Calculate")
      check-tmm-btn (new JButton "Check")
      reset-tmm-btn (new JButton "Reset")
      tmm-btn (new JButton "NMM")
      export-tmm (new JButton "Export")

      fields-one [par3-text par4-text]
      fields-zero [par1-text par2-text  par7-text par8-text par10-text par11-text par12-text par13-text par14-text
                   par15-text par16-text par17-text par18-text par19-text par20-text  par21-text  par22-text  par23-text  par24-text]
      fields-expr [ par6-text  par9-text par25-text par26-text par27-text exp1-text exp2-text exp3-text exp4-text exp5-text exp6-text inlet-text1 inlet-text2 inlet-text3 inlet-text4 inlet-text5
                   chamber-text1 chamber-text3 q-text2 jet-text1 jet-text2 jet-text3 a-text b-text c-text g-text f-text fsp-text mg-text csp-text1 csp-text2 mv-text c1-text theta-text]
      labels-one [par3-label par4-label]
      labels-zero [par1-label par2-label   par7-label par8-label  par10-label par11-label par12-label par13-label par14-label
                   par15-label par16-label par17-label par18-label par19-label par20-label  par21-label  par22-label  par23-label  par24-label]
      labels-expr [ par6-label par9-label other-label q-label jet-label chamber-label inlet-label  expression-label par25-label par26-label par27-label exp1-label exp2-label exp3-label exp4-label exp5-label exp6-label inlet-label1 inlet-label2 inlet-label3 inlet-label4 inlet-label5
                   chamber-label1 chamber-label3 q-label2 jet-label1 jet-label2 jet-label3 a-label b-label c-label g-label f-label fsp-label mg-label csp-label mv-label c1-label theta-label4]
      btns [nmm-btn check-btn calc-nmm-button reset-btn export-nmm]

      tmm-fields-one [tmm-par3-text tmm-par4-text]
      tmm-fields-zero [tmm-par1-text tmm-par2-text tmm-par6-text tmm-par7-text  tmm-par9-text tmm-par10-text tmm-par11-text tmm-par12-text tmm-par13-text tmm-par14-text tmm-par15-text tmm-par16-text
                       tmm-par17-text tmm-par18-text tmm-par19-text tmm-par20-text  tmm-par27-text tmm-par28-text tmm-par29-text]
      tmm-fields-expr [tmm-par5-text tmm-par8-text tmm-par24-text tmm-par25-text tmm-par26-text tmm-exp1-text tmm-exp2-text tmm-exp3-text tmm-exp4-text tmm-exp5-text tmm-exp6-text tmm-inlet-text1 tmm-inlet-text2 tmm-inlet-text3 tmm-inlet-text4 tmm-inlet-text5
                       tmm-compressor-text1  tmm-compressor-text2  tmm-compressor-text3  tmm-compressor-text4  tmm-compressor-text5 tmm-chamber-text1 tmm-chamber-text2 tmm-chamber-text3  tmm-turbine-text1 tmm-turbine-text2
                       tmm-turbine-text3 tmm-turbine-text4 tmm-turbine-text5 tmm-turbine-text6 tmm-jet-text1 tmm-jet-text2 tmm-jet-text3 tmm-jet-text4 tmm-jet-text5 tmm-jet-text6 tmm-jet-text7 tmm-other-text1 tmm-other-text2 tmm-other-text3 tmm-other-text4]
      tmm-labels-one [tmm-par3-label tmm-par4-label]
      tmm-labels-zero [tmm-par1-label tmm-par2-label  tmm-par6-label tmm-par7-label  tmm-par9-label tmm-par10-label tmm-par11-label tmm-par12-label tmm-par13-label tmm-par14-label tmm-par15-label tmm-par16-label
                       tmm-par17-label tmm-par18-label tmm-par19-label tmm-par20-label tmm-par27-label tmm-par28-label tmm-par29-label]
      tmm-labels-expr [tmm-par5-label tmm-par8-label tmm-par24-label tmm-par25-label tmm-par26-label tmm-exp1-label tmm-exp2-label tmm-exp3-label tmm-exp4-label tmm-exp5-label tmm-exp6-label tmm-inlet-label1 tmm-inlet-label2 tmm-inlet-label3 tmm-inlet-label4 tmm-inlet-label5
                       tmm-compressor-label1  tmm-compressor-label2  tmm-compressor-label3  tmm-compressor-label4  tmm-compressor-label5 tmm-chamber-label1 tmm-chamber-label2 tmm-chamber-label3  tmm-turbine-label1 tmm-turbine-label2
                       tmm-turbine-label3 tmm-turbine-label4 tmm-turbine-label5 tmm-turbine-label6 tmm-jet-label1 tmm-jet-label2 tmm-jet-label3 tmm-jet-label4 tmm-jet-label5 tmm-jet-label6 tmm-jet-label7 tmm-other-label1 tmm-other-label2 tmm-other-label3 tmm-other-label tmm-expression-label
                       tmm-inlet-label tmm-compressor-label tmm-chamber-label tmm-turbine-label tmm-jet-label tmm-other-label]
      tmm-btns [calc-tmm-button check-tmm-btn reset-tmm-btn tmm-btn export-tmm]]

  (doseq [frm [login-frame accounts-frame tmm-frame nmm-frame main-frame]]
    (.setIconImage frm (ImageIO/read (io/resource "ico/aircraft-maintenance.png"))))


 (defn hover [btn]
   (.addMouseListener btn
                      (proxy [MouseAdapter] []
                        (mouseEntered [e]
                          (.setBackground btn (Color. 135 206 250)))
                        (mouseExited [e]
                          (.setBackground btn (Color. 173 216 230))))))

  (defn write-pdf [file-path data]
    (let [doc (PDDocument.)
          page (PDPage. (PDRectangle. 950 750))  ;; A4 Landscape
          font (PDType0Font/load doc (File. "resources/fonts/DejaVuSansMono.ttf"))
          font-size 12
          line-height 14
          y-start 730
          margin 50]
      (.addPage doc page)
      (with-open [content-stream (PDPageContentStream. doc page)]
        (.setFont content-stream font font-size)
        (.beginText content-stream)
        (.newLineAtOffset content-stream margin y-start)

        (doseq [row data]
          (doseq [text row]
            (.showText content-stream text))
          (.newLineAtOffset content-stream 0 (- line-height)))

        (.endText content-stream))
      (.save doc file-path)
      (.close doc)))

;;login-frame
  (.setLayout login-frame nil)
  (.setBounds username-label 50 30 75 30)
  (.setBounds username-text 130 30 120 30)
  (.setBounds password-label 50 70 75 30)
  (.setBounds password-text 130 70 120 30)
  (.setBounds login-button 120 130 80 30)
  (.setBackground login-button (Color. 173 216 230))
  (.setBackground (.getContentPane login-frame) (Color. 227 242 253))

 (doseq [btn [login-button add-button delete-button update-button nmm-btn check-btn calc-nmm-button reset-btn calc-tmm-button check-tmm-btn reset-tmm-btn tmm-btn main-nmm-button main-tmm-button export-tmm export-nmm]]
   (hover btn))

(.addActionListener login-button
                    (proxy [ActionListener] []
                      (actionPerformed [evt]
                        (let [host "localhost"
                              dbname "Clojure"
                              username (.getText username-text)
                              password (String. (.getPassword password-text))
                              account-type (db/authenticate host dbname username password)]
                          (cond
                            (= account-type "Admin") (do (JOptionPane/showMessageDialog nil "Login successful!") (.setVisible accounts-frame true)  (.setVisible login-frame false))
                            (= account-type "TMM") (do (JOptionPane/showMessageDialog nil "Login successful!") (.setVisible tmm-frame true) (.setVisible tmm-btn false) (.setVisible login-frame false))
                            (= account-type "NMM") (do (JOptionPane/showMessageDialog nil "Login successful!") (.setVisible nmm-frame true) (.setVisible nmm-btn false) (.setVisible login-frame false))
                            (= account-type "Director") (do (JOptionPane/showMessageDialog nil "Login successful!") (.setVisible main-frame true) (.setVisible login-frame false))
                            :else (JOptionPane/showMessageDialog nil "Invalid username or password.") )))))

;;accounts-frame
  (.setLayout accounts-frame (new FlowLayout))
  (doseq [user (db/get-all-users "localhost" "Clojure")]
    (.addRow table-model (into-array [(get user :username) (get user :password) (get user :type)])))
  (.setSelectedIndex account-type-combo -1)
  (.setBackground add-button (Color. 173 216 230))
  (.setBackground delete-button (Color. 173 216 230))
  (.setBackground update-button (Color. 173 216 230))
  (.setBackground (.getContentPane accounts-frame) (Color. 227 242 253))

  (.addMouseListener user-table
                     (proxy [MouseAdapter] []
                       (mouseClicked [e]
                         (let [selected-row (.getSelectedRow user-table)
                               username (.getValueAt user-table selected-row 0)
                               password (.getValueAt user-table selected-row 1)
                               account-type (.getValueAt user-table selected-row 2)]
                          (.setText username-acc-text (str username))
                          (.setText password-acc-text (str password))
                          (.setSelectedItem account-type-combo (str account-type))))))


  (.addActionListener add-button
                    (proxy [ActionListener] []
                      (actionPerformed [e]
                        (let [username (.getText username-acc-text)
                              password (.getText password-acc-text)
                              account-type (.getSelectedItem account-type-combo)
                              host "localhost"
                              dbname "Clojure"]
                          (if (and (not (empty? username))
                                   (not (empty? password))
                                   (not (empty? account-type)))
                            (try
                              (db/add-user host dbname username password (str account-type))
                              (.addRow table-model (into-array [username password account-type]))
                              (.setText username-acc-text "")
                              (.setText password-acc-text "")
                              (JOptionPane/showMessageDialog accounts-frame "User added successfully!")
                              (catch Exception ex
                                (JOptionPane/showMessageDialog accounts-frame
                                                               (str "Failed to add user: " (.getMessage ex))
                                                               "Error"
                                                               JOptionPane/ERROR_MESSAGE)))
                            (JOptionPane/showMessageDialog accounts-frame "All fields are required!"))))))


  (.addActionListener delete-button
                    (proxy [ActionListener] []
                      (actionPerformed [e]
                        (let [selected-row (.getSelectedRow user-table)
                              host "localhost"
                              dbname "Clojure"]
                          (if (>= selected-row 0)
                            (let [username (.getValueAt (.getModel user-table) selected-row 0)]
                              (try
                                (db/delete-user host dbname username)
                                (.removeRow table-model selected-row)
                                (.setText username-acc-text "")
                                (.setText password-acc-text "")
                                (JOptionPane/showMessageDialog accounts-frame "User deleted successfully!")
                                (catch Exception ex
                                  (JOptionPane/showMessageDialog accounts-frame
                                                                 (str "Failed to delete user: " (.getMessage ex))
                                                                 "Error"
                                                                 JOptionPane/ERROR_MESSAGE))))
                            (JOptionPane/showMessageDialog accounts-frame "Please select a user to delete!"))))))

    (.addActionListener update-button
                      (proxy [ActionListener] []
                        (actionPerformed [e]
                          (let [selected-row (.getSelectedRow user-table)
                                host "localhost"
                                dbname "Clojure"
                                username (.getText username-acc-text)
                                new-password (.getText password-acc-text)
                                new-account-type (.getSelectedItem account-type-combo)]
                            (if (>= selected-row 0)
                              (try
                                (db/update-user host dbname username new-password new-account-type)
                                (.setValueAt (.getModel user-table) new-password selected-row 1)
                                (.setValueAt (.getModel user-table) new-account-type selected-row 2)
                                (JOptionPane/showMessageDialog accounts-frame "User updated successfully!")
                                (catch Exception ex
                                  (JOptionPane/showMessageDialog accounts-frame
                                                                 (str "Failed to update user: " (.getMessage ex))
                                                                 "Error"
                                                                 JOptionPane/ERROR_MESSAGE)))
                              (JOptionPane/showMessageDialog accounts-frame "Please select a user to update!"))))))

;;main-frame
  (.setLayout main-frame nil)
  (.setBounds main-nmm-button 20 0 100 50)
  (.setBounds main-tmm-button 200 0 100 50)
  (.setBackground (.getContentPane main-frame) (Color. 227 242 253))
  (.setBackground main-nmm-button (Color. 173 216 230))
  (.setBackground main-tmm-button (Color. 173 216 230))

  (.addActionListener main-nmm-button
                      (proxy [ActionListener] []
                        (actionPerformed [evt]
                          (.setVisible main-frame false)
                          (.setVisible nmm-frame true))))

  (.addActionListener main-tmm-button
                      (proxy [ActionListener] []
                        (actionPerformed [evt]
                          (.setVisible main-frame false)
                          (.setVisible tmm-frame true))))

  ;;nmm-frame
  (.setLayout nmm-frame nil)
  (.setBackground (.getContentPane nmm-frame) (Color. 227 242 253))
  (.setBounds par1-label 0 5 40 30)
  (.setBounds par1-text 50 5 50 30)
  (.setBounds par2-label 110 5 40 30)
  (.setBounds par2-text 160 5 50 30)
  (.setBounds par3-label 220 5 40 30)
  (.setBounds par3-text 270 5 50 30)
  (.setBounds par4-label 330 5 40 30)
  (.setBounds par4-text 380 5 50 30)
  (.setBounds par24-label 0 45 40 30)
  (.setBounds par24-text 50 45 50 30)
  (.setBounds par6-label 110 45 40 30)
  (.setBounds par6-text 160 45 50 30)
  (.setEditable par6-text false)
  (.setBounds par7-label 220 45 40 30)
  (.setBounds par7-text 270 45 50 30)
  (.setBounds par8-label 330 45 40 30)
  (.setBounds par8-text 380 45 50 30)
  (.setBounds par9-label 0 85 40 30)
  (.setBounds par9-text 50 85 50 30)
  (.setEditable par9-text false)
  (.setBounds par10-label 110 85 40 30)
  (.setBounds par10-text 160 85 50 30)
  (.setBounds par11-label 220 85 40 30)
  (.setBounds par11-text 270 85 50 30)
  (.setBounds par12-label 330 85 40 30)
  (.setBounds par12-text 380 85 50 30)
  (.setBounds par13-label 0 125 40 30)
  (.setBounds par13-text 50 125 50 30)
  (.setBounds par14-label 110 125 40 30)
  (.setBounds par14-text 160 125 50 30)
  (.setBounds par15-label 220 125 40 30)
  (.setBounds par15-text 270 125 50 30)
  (.setBounds par16-label 330 125 40 30)
  (.setBounds par16-text 380 125 50 30)
  (.setBounds par17-label 0 165 40 30)
  (.setBounds par17-text 50 165 50 30)
  (.setBounds par18-label 110 165 40 30)
  (.setBounds par18-text 160 165 50 30)
  (.setBounds par19-label 220 165 40 30)
  (.setBounds par19-text 270 165 50 30)
  (.setBounds par20-label 330 165 40 30)
  (.setBounds par20-text 380 165 50 30)
  (.setBounds par21-label 0 205 40 30)
  (.setBounds par21-text 50 205 50 30)
  (.setBounds par22-label 110 205 40 30)
  (.setBounds par22-text 160 205 50 30)
  (.setBounds par23-label 220 205 40 30)
  (.setBounds par23-text 270 205 50 30)
  (.setBounds par25-label 330 205 40 30)
  (.setBounds par25-text 380 205 50 30)
  (.setEditable par25-text false)
  (.setBounds par26-label 110 245 40 30)
  (.setBounds par26-text 160 245 50 30)
  (.setEditable par26-text false)
  (.setBounds par27-label 220 245 40 30)
  (.setBounds par27-text 270 245 60 30)
  (.setEditable par27-text false)
  (.setBounds check-btn 5 280 100 20)
  (.setBounds calc-nmm-button 110 280 100 20)
  (.setEnabled calc-nmm-button false)
  (.setBounds reset-btn 215 280 100 20)
  (.setEnabled reset-btn false)
  (.setBounds export-nmm 320 280 100 20)
  (.setEnabled export-nmm false)
  (.setBounds expression-label 180 300 80 20)
  (.setBounds exp1-label 5 325 60 30)
  (.setBounds exp1-text 70 325 50 30)
  (.setEditable exp1-text false)
  (.setBounds exp2-label 130 325 70 30)
  (.setBounds exp2-text 210 325 50 30)
  (.setEditable exp2-text false)
  (.setBounds exp3-label 280 325 60 30)
  (.setBounds exp3-text 350 325 50 30)
  (.setEditable exp3-text false)
  (.setBounds exp4-label 5 365 60 30)
  (.setBounds exp4-text 70 365 50 30)
  (.setEditable exp4-text false)
  (.setBounds exp5-label 130 365 70 30)
  (.setBounds exp5-text 210 365 50 30)
  (.setEditable exp5-text false)
  (.setBounds exp6-label 280 365 60 30)
  (.setBounds exp6-text 350 365 50 30)
  (.setEditable exp6-text false)
  (.setBounds inlet-label 195 395 60 20)
  (.setBounds inlet-label1 5 420 40 30)
  (.setBounds inlet-text1 55 420 70 30)
  (.setEditable inlet-text1 false)
  (.setBounds inlet-label2 130 420 50 30)
  (.setBounds inlet-text2 195 420 70 30)
  (.setEditable inlet-text2 false)
  (.setBounds inlet-label3 280 420 60 30)
  (.setBounds inlet-text3 350 420 50 30)
  (.setEditable inlet-text3 false)
  (.setBounds inlet-label4 65 460 60 30)
  (.setBounds inlet-text4 130 460 50 30)
  (.setEditable inlet-text4 false)
  (.setBounds inlet-label5 220 460 40 30)
  (.setBounds inlet-text5 270 460 50 30)
  (.setEditable inlet-text5 false)
  (.setBounds chamber-label 160 490 120 15)
  (.setBounds chamber-label1 55 510 20 20)
  (.setBounds chamber-text1 105 510 70 30)
  (.setEditable chamber-text1 false)
  (.setBounds chamber-label3 210 510 40 20)
  (.setBounds chamber-text3 260 510 50 30)
  (.setEditable chamber-text3 false)
  (.setBounds jet-label 210 545 40 20)
  (.setBounds jet-label1 5 570 30 20)
  (.setBounds jet-text1 70 570 50 30)
  (.setEditable jet-text1 false)
  (.setBounds jet-label2 130 570 25 20)
  (.setBounds jet-text2 210 570 50 30)
  (.setEditable jet-text2 false)
  (.setBounds jet-label3 280 570 25 20)
  (.setBounds jet-text3 350 570 50 30)
  (.setEditable jet-text3 false)
  (.setBounds other-label 175 600 130 25)
  (.setBounds g-label 5 630 20 20)
  (.setBounds g-text 30 630 70 30)
  (.setEditable g-text false)
  (.setBounds f-label 130 630 20 20)
  (.setBounds f-text 160 630 70 30)
  (.setEditable f-text false)
  (.setBounds fsp-label 260 630 35 20)
  (.setBounds fsp-text 330 630 70 30)
  (.setEditable fsp-text false)
  (.setBounds q-label 110 670 230 25)
  (.setBounds a-label 5 700 20 20)
  (.setBounds a-text 30 700 60 30)
  (.setEditable a-text false)
  (.setBounds b-label 100 700 20 20)
  (.setBounds b-text 130 700 60 30)
  (.setEditable b-text false)
  (.setBounds c-label 200 700 20 20)
  (.setBounds c-text 230 700 60 30)
  (.setEditable c-text false)
  (.setBounds q-label2 300 700 20 20)
  (.setBounds q-text2 330 700 60 30)
  (.setEditable q-text2 false)
  (.setBounds mg-label 5 740 20 20)
  (.setBounds mg-text 30 740 60 30)
  (.setEditable mg-text false)
  (.setBounds csp-label 100 740 40 20)
  (.setBounds csp-text1 150 740 60 30)
  (.setBounds csp-text2 220 740 60 30)
  (.setEditable csp-text1 false)
  (.setEditable csp-text2 false)
  (.setBounds theta-label4 290 740 20 20)
  (.setBounds theta-text 320 740 50 30)
  (.setEditable theta-text false)
  (.setBounds mv-label 5 780 30 20)
  (.setBounds mv-text 40 780 60 30)
  (.setEditable mv-text false)
  (.setBounds c1-label 110 780 40 20)
  (.setBounds c1-text 160 780 60 30)
  (.setEditable c1-text false)
  (.setBounds nmm-btn 330 790 100 20)
  (.setBackground check-btn (Color. 173 216 230))
  (.setBackground calc-nmm-button (Color. 173 216 230))
  (.setBackground reset-btn (Color. 173 216 230))
  (.setBackground nmm-btn (Color. 173 216 230))
  (.setBackground export-nmm (Color. 173 216 230))


  (.addActionListener nmm-btn
                      (proxy [ActionListener] []
                        (actionPerformed [evt]
                          (.setVisible nmm-frame false)
                          (.setVisible tmm-frame true)
                          (doseq [field (concat fields-zero fields-one fields-expr)]
                            (.setText field "")
                            (when (or (some #(= field %) fields-zero)
                                      (some #(= field %) fields-one))
                              (.setEditable field true)))
                          (.setEnabled check-btn true)
                          (.setEnabled reset-btn false)
                          (.setEnabled calc-nmm-button false))))

  (.addActionListener check-btn
                      (proxy [ActionListener] []
                        (actionPerformed [evt]
                          (let [validation-result (proj/validate-fields fields-zero fields-one)]
                            (if (:valid validation-result)
                              (do
                                (.setEnabled calc-nmm-button true)
                                (.setEnabled check-btn false)
                                (doseq [field (concat fields-zero fields-one)]
                                  (.setEditable field false)))
                              (do
                                (cond
                                  (some #(<= % 0) (:values-zero validation-result))
                                  (JOptionPane/showMessageDialog nil "All values (except kv, kps) must be greater than 0.")
                                  (some #(not (proj/greater-than-one? % )) (:values-one validation-result))
                                  (JOptionPane/showMessageDialog nil "Some values (kv,kps) must be greater than 1."))
                                ))))))

(.addActionListener reset-btn
                    (proxy [ActionListener] []
                      (actionPerformed [evt]
                        (doseq [field (concat fields-zero fields-one fields-expr)]
                          (.setText field "")
                          (when (or (some #(= field %) fields-zero)
                                    (some #(= field %) fields-one))
                            (.setEditable field true)))
                        (.setEnabled calc-nmm-button false)
                        (.setEnabled export-nmm false)
                        (.setEnabled check-btn true)
                        (.setEnabled reset-btn false))))


(.addActionListener calc-nmm-button
                    (proxy [ActionListener] []
                      (actionPerformed [evt]
                        (.setEnabled reset-btn true)
                        (.setEnabled export-nmm true)
                        (.setEnabled check-btn false)
                        (.setEnabled calc-nmm-button false)
                        (.setText par25-text (format "%.3f" (proj/T0 (Double/parseDouble (.getText par8-text)))))
                        (.setText par9-text (format "%.3f" (proj/a0 (Double/parseDouble(.getText par3-text)) (Double/parseDouble (.getText par19-text)) (Double/parseDouble (.getText par25-text)))))
                        (.setText par6-text (format "%.3f" (proj/multiplication (Double/parseDouble(.getText par9-text)) (Double/parseDouble (.getText par10-text)))))
                        (.setText par26-text (format "%.3f" (proj/ro0 (Double/parseDouble (.getText par8-text)))))
                        (.setText par27-text (format "%.3f" (proj/p0 (Double/parseDouble (.getText par8-text)))))
                        (.setText exp1-text (format "%.3f" (proj/fn1 (Double/parseDouble (.getText par3-text)))))
                        (.setText exp2-text (format "%.3f" (proj/fn1 (Double/parseDouble (.getText par4-text)))))
                        (.setText exp3-text (format "%.3f" (proj/fn2 (Double/parseDouble (.getText par3-text)))))
                        (.setText exp4-text (format "%.3f" (proj/fn3 (Double/parseDouble (.getText par4-text)))))
                        (.setText exp5-text (format "%.3f" (proj/fn2 (Double/parseDouble (.getText par4-text)))))
                        (.setText exp6-text (format "%.3f" (proj/fn4 (Double/parseDouble (.getText par3-text)))))
                        (.setText inlet-text1 (format "%.3f" (proj/p0* (Double/parseDouble(.getText par27-text)) (Double/parseDouble (.getText par3-text)) (Double/parseDouble (.getText par10-text)))))
                        (.setText inlet-text2 (format "%.3f" (proj/multiplication (Double/parseDouble(.getText inlet-text1)) (Double/parseDouble (.getText par11-text)))))
                        (.setText inlet-text4 (format "%.3f" (proj/division (Double/parseDouble(.getText inlet-text2)) (Double/parseDouble (.getText par27-text)))))
                        (.setText inlet-text5 (format "%.3f" (proj/tauu (Double/parseDouble(.getText par3-text)) (Double/parseDouble (.getText par10-text)))))
                        (.setText inlet-text3 (format "%.3f" (proj/multiplication (Double/parseDouble(.getText inlet-text5)) (Double/parseDouble (.getText par25-text)))))
                        (.setText chamber-text1 (format "%.3f" (proj/multiplication (Double/parseDouble(.getText par13-text)) (Double/parseDouble (.getText inlet-text2)))))
                        (.setText jet-text1 (format "%.3f" (proj/division (Double/parseDouble(.getText chamber-text1)) (Double/parseDouble (.getText par27-text)))))
                        (.setText jet-text2 (format "%.3f" (proj/conv-pim (Double/parseDouble(.getText jet-text1)) (Double/parseDouble (.getText par21-text)))))
                        (.setText g-text (format "%.3f" (proj/multiplication (Double/parseDouble(.getText par23-text)) (Double/parseDouble (.getText par24-text)))))
                        (.setText f-text (format "%.3f" (proj/division (Double/parseDouble(.getText g-text)) (Double/parseDouble (.getText par22-text)))))
                        (.setText mv-text (format "%.3f" (proj/multiplication (Double/parseDouble(.getText par6-text)) (Double/parseDouble (.getText par26-text)) (Double/parseDouble (.getText par7-text)))))
                        (.setText c1-text (format "%.3f" (proj/easy-calculation (Double/parseDouble(.getText par2-text)) (Double/parseDouble (.getText par25-text)) (Double/parseDouble (.getText jet-text1)) (Double/parseDouble (.getText par4-text)))))
                        (.setText a-text (format "%.3f" (proj/coeff-a (Double/parseDouble(.getText par14-text)) (Double/parseDouble(.getText par18-text))(Double/parseDouble (.getText par1-text)) (Double/parseDouble (.getText par25-text)))))
                        (.setText b-text (format "%.3f" (proj/coeff-b (Double/parseDouble(.getText inlet-text5)) (Double/parseDouble (.getText a-text)))))
                        (.setText fsp-text (format "%.3f" (proj/division (Double/parseDouble(.getText f-text)) (Double/parseDouble (.getText mv-text)))))
                        (.setText c-text (format "%.3f" (proj/coeff-c (Double/parseDouble(.getText inlet-text5)) (Double/parseDouble (.getText par2-text)) (Double/parseDouble(.getText par1-text)) (Double/parseDouble(.getText par6-text)) (Double/parseDouble(.getText fsp-text)) (Double/parseDouble(.getText c1-text))(Double/parseDouble(.getText par17-text)))))
                        (.setText q-text2 (format "%.3f" (proj/ratio-fuel-air (Double/parseDouble(.getText a-text)) (Double/parseDouble (.getText b-text)) (Double/parseDouble (.getText c-text)))))
                        (.setText jet-text3 (format "%.3f" (proj/vi (Double/parseDouble(.getText fsp-text)) (Double/parseDouble (.getText par6-text)) (Double/parseDouble (.getText q-text2)))))
                        (.setText theta-text (format "%.3f" (proj/degree-of-heating (Double/parseDouble(.getText jet-text3)) (Double/parseDouble (.getText c1-text)) (Double/parseDouble (.getText par17-text)))))
                        (.setText chamber-text3 (format "%.3f" (proj/multiplication (Double/parseDouble(.getText par25-text)) (Double/parseDouble (.getText theta-text)))))
                        (.setText mg-text (format "%.3f" (proj/multiplication (Double/parseDouble(.getText q-text2)) (Double/parseDouble (.getText mv-text)))))
                        (.setText csp-text1 (format "%.6f" (proj/division (Double/parseDouble(.getText mg-text)) (Double/parseDouble (.getText f-text)))))
                        (.setText csp-text2 (format "%.3f" (proj/multiplication (Double/parseDouble(.getText csp-text1)) 36000)))
                        )))


  (.addActionListener export-nmm
                      (proxy [ActionListener] []
                        (actionPerformed [evt]
                          (let [file-path (str (System/getProperty "user.home") "/Desktop/NMM.pdf")
                                data [[(format "%-12s %-12s %-2s %-12s %-12s %-2s %-8s %-22s %-2s %-12s %-12s" "Data" "Value" "" "0-1*" "Inlet" ""  "2*-3*"  "Combustion chamber" "" "4*-5" "Jet")]
                                      ["------------------------------------------------------------------------------------------------------------------------"]
                                      [(format "%-12s %-12s %-2s %-12s %-12s %-2s %-8s %-22s %-2s %-12s %-12s" "Cpv" (.getText par1-text) ""  "p0*" (.getText inlet-text1) ""  "p2*"  (.getText chamber-text1) ""  "πmr" (.getText jet-text1))]
                                      ["------------------------------------------------------------------------------------------------------------------------"]
                                      [(format "%-12s %-12s %-2s %-12s %-12s %-2s %-8s %-22s %-2s %-12s %-12s" "Cpps" (.getText par2-text) ""  "p1*" (.getText inlet-text2) ""  "q"  (.getText q-text2) ""  "πm" (.getText jet-text2))]
                                      ["------------------------------------------------------------------------------------------------------------------------"]
                                      [(format "%-12s %-12s %-2s %-12s %-12s %-2s %-8s %-22s %-2s %-12s %-12s" "kv" (.getText par3-text) ""  "T1*" (.getText inlet-text3) ""  "T2*"  (.getText chamber-text3) ""  "Vi" (.getText jet-text3))]
                                      ["------------------------------------------------------------------------------------------------------------------------"]
                                      [(format "%-12s %-12s %-2s %-12s %-12s %-2s %-8s %-22s %-2s %-12s %-12s" "kps" (.getText par4-text) ""  "πu" (.getText inlet-text4) ""  "θ"  (.getText theta-text) ""  "πkrit" (.getText par21-text))]
                                      ["------------------------------------------------------------------------------------------------------------------------"]
                                      [(format "%-12s %-12s %-2s %-12s %-12s" "T0" (.getText par25-text) ""  "τu" (.getText inlet-text5))]
                                      ["------------------------------------------------------------------------------------------------------------------------"]
                                      [(format "%-12s %-12s %-2s %-12s %-12s %-2s %-8s %-22s" "v0" (.getText par6-text) ""  "" "" "" "mg"  (.getText mg-text))]
                                      ["------------------------------------------------------------------------------------------------------------------------"]
                                      [(format "%-12s %-12s %-2s %-12s %-12s %-2s %-8s %-14s %-5s %-12s %-12s" "ρ0" (.getText par26-text) ""  "" "" ""  "Csp"  (.getText csp-text1) "kg/Ns"  (.getText csp-text2) "kg/daNh")]
                                      ["------------------------------------------------------------------------------------------------------------------------"]
                                      [(format "%-12s %-12s" "Au" (.getText par7-text))]
                                      ["------------------------------------------------------------------------------------------------------------------------"]
                                      [(format "%-12s %-12s %-2s %-12s %-12s" "H" (.getText par8-text) ""  "Cz/Cx" (.getText par22-text))]
                                      ["------------------------------------------------------------------------------------------------------------------------"]
                                      [(format "%-12s %-12s %-2s %-12s %-12s" "p0" (.getText par27-text) ""  "m" (.getText par23-text))]
                                      ["------------------------------------------------------------------------------------------------------------------------"]
                                      [(format "%-12s %-12s %-2s %-12s %-12s" "a0" (.getText par9-text) ""  "g" (.getText par24-text))]
                                      ["------------------------------------------------------------------------------------------------------------------------"]
                                      [(format "%-12s %-12s %-2s %-12s %-12s %-2s %-8s %-22s" "M0" (.getText par10-text) ""  "G" (.getText g-text) ""  ""  "Quadratic equations")]
                                      ["------------------------------------------------------------------------------------------------------------------------"]
                                      [(format "%-12s %-12s %-2s %-12s %-12s %-2s %-8s %-2s %-12s" "mv" (.getText mv-text) ""  "F" (.getText f-text) ""  ""  "a" (.getText a-text))]
                                      ["------------------------------------------------------------------------------------------------------------------------"]
                                      [(format "%-12s %-12s %-2s %-12s %-12s %-2s %-8s %-2s %-12s" "σu"  (.getText par11-text) ""  "Fsp" (.getText fsp-text) ""  ""  "b" (.getText b-text))]
                                      ["------------------------------------------------------------------------------------------------------------------------"]
                                      [(format "%-12s %-12s %-2s %-12s %-12s %-2s %-8s %-2s %-12s" "ηk" (.getText par12-text) ""  "" "" ""  ""  "c" (.getText c-text))]
                                      ["------------------------------------------------------------------------------------------------------------------------"]
                                      [(format "%-12s %-12s %-2s %-12s %-12s" "σp" (.getText par13-text) ""  "kv/(kv-1)" (.getText exp1-text))]
                                      ["------------------------------------------------------------------------------------------------------------------------"]
                                      [(format "%-12s %-12s %-2s %-12s %-12s" "σg" (.getText par14-text) ""  "kps/(kps-1)" (.getText exp2-text))]
                                      ["------------------------------------------------------------------------------------------------------------------------"]
                                      [(format "%-12s %-12s %-2s %-12s %-12s" "ηt" (.getText par15-text) ""  "(kv-1)/kv" (.getText exp3-text))]
                                      ["------------------------------------------------------------------------------------------------------------------------"]
                                      [(format "%-12s %-12s %-2s %-12s %-12s" "ηm" (.getText par16-text) ""  "1/(kps-1)" (.getText exp4-text))]
                                      ["------------------------------------------------------------------------------------------------------------------------"]
                                      [(format "%-12s %-12s %-2s %-12s %-12s" "φ" (.getText par17-text) ""  "(kps-1)/kps" (.getText exp5-text))]
                                      ["------------------------------------------------------------------------------------------------------------------------"]
                                      [(format "%-12s %-12s %-2s %-12s %-12s" "Hd" (.getText par18-text) ""  "(kv-1)/2" (.getText exp6-text))]
                                      ["------------------------------------------------------------------------------------------------------------------------"]
                                      [(format "%-12s %-12s %-2s %-12s %-12s" "Rv" (.getText par19-text) ""  "C1" (.getText c1-text))]
                                      ["------------------------------------------------------------------------------------------------------------------------"]
                                      [(format "%-12s %-12s" "Rps" (.getText par20-text))]
                                      ["------------------------------------------------------------------------------------------------------------------------"]
                                      ]]
                                (write-pdf file-path data)))))


  ;;tmm-frame
  (.setLayout tmm-frame nil)
  (.setBackground (.getContentPane tmm-frame) (Color. 227 242 253))
  (.setBounds tmm-par1-label 0 5 40 30)
  (.setBounds tmm-par1-text 50 5 50 30)
  (.setBounds tmm-par2-label 110 5 40 30)
  (.setBounds tmm-par2-text 160 5 50 30)
  (.setBounds tmm-par3-label 220 5 40 30)
  (.setBounds tmm-par3-text 270 5 50 30)
  (.setBounds tmm-par4-label 330 5 40 30)
  (.setBounds tmm-par4-text 380 5 50 30)
  (.setBounds tmm-par5-label 440 5 40 30)
  (.setBounds tmm-par5-text 490 5 50 30)
  (.setEditable tmm-par5-text false)
  (.setBounds tmm-par6-label 550 5 40 30)
  (.setBounds tmm-par6-text 600 5 50 30)
  (.setBounds tmm-par7-label 0 45 40 30)
  (.setBounds tmm-par7-text 50 45 50 30)
  (.setBounds tmm-par8-label 110 45 40 30)
  (.setBounds tmm-par8-text 160 45 50 30)
  (.setEditable tmm-par8-text false)
  (.setBounds tmm-par9-label 220 45 40 30)
  (.setBounds tmm-par9-text 270 45 50 30)
  (.setBounds tmm-par10-label 330 45 40 30)
  (.setBounds tmm-par10-text 380 45 50 30)
  (.setBounds tmm-par11-label 440 45 40 30)
  (.setBounds tmm-par11-text 490 45 50 30)
  (.setBounds tmm-par12-label 550 45 40 30)
  (.setBounds tmm-par12-text 600 45 50 30)
  (.setBounds tmm-par13-label 0 85 40 30)
  (.setBounds tmm-par13-text 50 85 50 30)
  (.setBounds tmm-par14-label 110 85 40 30)
  (.setBounds tmm-par14-text 160 85 50 30)
  (.setBounds tmm-par15-label 220 85 40 30)
  (.setBounds tmm-par15-text 270 85 50 30)
  (.setBounds tmm-par16-label 330 85 40 30)
  (.setBounds tmm-par16-text 380 85 50 30)
  (.setBounds tmm-par17-label 440 85 40 30)
  (.setBounds tmm-par17-text 490 85 50 30)
  (.setBounds tmm-par18-label 550 85 40 30)
  (.setBounds tmm-par18-text 600 85 50 30)
  (.setBounds tmm-par19-label 0 125 40 30)
  (.setBounds tmm-par19-text 50 125 50 30)
  (.setBounds tmm-par20-label 110 125 40 30)
  (.setBounds tmm-par20-text 160 125 50 30)
  (.setBounds tmm-par27-label 220 125 40 30)
  (.setBounds tmm-par27-text 270 125 50 30)
  (.setBounds tmm-par28-label 330 125 40 30)
  (.setBounds tmm-par28-text 380 125 50 30)
  (.setBounds tmm-par29-label 440 125 40 30)
  (.setBounds tmm-par29-text 490 125 50 30)
  (.setBounds tmm-par24-label 550 125 40 30)
  (.setBounds tmm-par24-text 600 125 50 30)
  (.setEditable tmm-par24-text false)
  (.setBounds tmm-par25-label 220 165 40 30)
  (.setBounds tmm-par25-text 270 165 50 30)
  (.setEditable tmm-par25-text false)
  (.setBounds tmm-par26-label 330 165 40 30)
  (.setBounds tmm-par26-text 380 165 70 30)
  (.setEditable tmm-par26-text false)
  (.setBounds check-tmm-btn 110 205 100 20)
  (.setBounds calc-tmm-button 220 205 100 20)
  (.setEnabled calc-tmm-button false)
  (.setBounds reset-tmm-btn 330 205 100 20)
  (.setEnabled reset-tmm-btn false)
  (.setBounds export-tmm 440 205 100 20)
  (.setEnabled export-tmm false)
  (.setBounds tmm-btn 580 720 100 20)
  (.setBounds tmm-expression-label 305 225 80 20)
  (.setBounds tmm-exp1-label 0 245 60 30)
  (.setBounds tmm-exp1-text 70 245 50 30)
  (.setEditable tmm-exp1-text false)
  (.setBounds tmm-exp2-label 130 245 70 30)
  (.setBounds tmm-exp2-text 210 245 50 30)
  (.setEditable tmm-exp2-text false)
  (.setBounds tmm-exp3-label 270 245 60 30)
  (.setBounds tmm-exp3-text 340 245 50 30)
  (.setEditable tmm-exp3-text false)
  (.setBounds tmm-exp4-label 400 245 60 30)
  (.setBounds tmm-exp4-text 470 245 50 30)
  (.setEditable tmm-exp4-text false)
  (.setBounds tmm-exp5-label 530 245 70 30)
  (.setBounds tmm-exp5-text 610 245 50 30)
  (.setEditable tmm-exp5-text false)
  (.setBounds tmm-exp6-label 270 275 60 30)
  (.setBounds tmm-exp6-text 340 275 50 30)
  (.setEditable tmm-exp6-text false)
  (.setBounds tmm-inlet-label 325 310 80 20)
  (.setBounds tmm-inlet-label1 0 330 40 30)
  (.setBounds tmm-inlet-text1 50 330 70 30)
  (.setEditable tmm-inlet-text1 false)
  (.setBounds tmm-inlet-label2 130 330 50 30)
  (.setBounds tmm-inlet-text2 190 330 70 30)
  (.setEditable tmm-inlet-text2 false)
  (.setBounds tmm-inlet-label3 270 330 60 30)
  (.setBounds tmm-inlet-text3 340 330 50 30)
  (.setEditable tmm-inlet-text3 false)
  (.setBounds tmm-inlet-label4 400 330 60 30)
  (.setBounds tmm-inlet-text4 470 330 50 30)
  (.setEditable tmm-inlet-text4 false)
  (.setBounds tmm-inlet-label5 530 330 70 30)
  (.setBounds tmm-inlet-text5 610 330 50 30)
  (.setEditable tmm-inlet-text5 false)
  (.setBounds tmm-compressor-label 305 365 80 20)
  (.setBounds tmm-compressor-label1 0 385 60 30)
  (.setBounds tmm-compressor-text1 70 385 50 30)
  (.setEditable tmm-compressor-text1 false)
  (.setBounds tmm-compressor-label2 130 385 50 30)
  (.setBounds tmm-compressor-text2 190 385 70 30)
  (.setEditable tmm-compressor-text2 false)
  (.setBounds tmm-compressor-label3 270 385 60 30)
  (.setBounds tmm-compressor-text3 340 385 50 30)
  (.setEditable tmm-compressor-text3 false)
  (.setBounds tmm-compressor-label4 400 385 60 30)
  (.setBounds tmm-compressor-text4 470 385 50 30)
  (.setEditable tmm-compressor-text4 false)
  (.setBounds tmm-compressor-label5 530 385 70 30)
  (.setBounds tmm-compressor-text5 610 385 50 30)
  (.setEditable tmm-compressor-text5 false)
  (.setBounds tmm-chamber-label 295 420 130 20)
  (.setBounds tmm-chamber-label1 130 440 40 30)
  (.setBounds tmm-chamber-text1 180 440 70 30)
  (.setEditable tmm-chamber-text1 false)
  (.setBounds tmm-chamber-label2 270 440 70 30)
  (.setBounds tmm-chamber-text2 340 440 50 30)
  (.setEditable tmm-chamber-text2 false)
  (.setBounds tmm-chamber-label3 400 440 60 30)
  (.setBounds tmm-chamber-text3 470 440 50 30)
  (.setEditable tmm-chamber-text3 false)
  (.setBounds tmm-turbine-label 315 475 80 20)
  (.setBounds tmm-turbine-label1 0 500 60 30)
  (.setBounds tmm-turbine-text1 70 500 50 30)
  (.setEditable tmm-turbine-text1 false)
  (.setBounds tmm-turbine-label2 130 500 50 30)
  (.setBounds tmm-turbine-text2 190 500 70 30)
  (.setEditable tmm-turbine-text2 false)
  (.setBounds tmm-turbine-label3 270 500 40 30)
  (.setBounds tmm-turbine-text3 320 500 70 30)
  (.setEditable tmm-turbine-text3 false)
  (.setBounds tmm-turbine-label4 400 500 60 30)
  (.setBounds tmm-turbine-text4 470 500 50 30)
  (.setEditable tmm-turbine-text4 false)
  (.setBounds tmm-turbine-label5 530 500 50 30)
  (.setBounds tmm-turbine-text5 590 500 70 30)
  (.setEditable tmm-turbine-text5 false)
  (.setBounds tmm-turbine-label6 270 530 40 30)
  (.setBounds tmm-turbine-text6 320 530 70 30)
  (.setEditable tmm-turbine-text6 false)
  (.setBounds tmm-jet-label 335 565 80 20)
  (.setBounds tmm-jet-label1 0 590 60 30)
  (.setBounds tmm-jet-text1 70 590 50 30)
  (.setEditable tmm-jet-text1 false)
  (.setBounds tmm-jet-label2 130 590 70 30)
  (.setBounds tmm-jet-text2 210 590 50 30)
  (.setEditable tmm-jet-text2 false)
  (.setBounds tmm-jet-label3 270 590 60 30)
  (.setBounds tmm-jet-text3 340 590 50 30)
  (.setEditable tmm-jet-text3 false)
  (.setBounds tmm-jet-label4 400 590 60 30)
  (.setBounds tmm-jet-text4 470 590 50 30)
  (.setEditable tmm-jet-text4 false)
  (.setBounds tmm-jet-label5 530 590 50 30)
  (.setBounds tmm-jet-text5 590 590 70 30)
  (.setEditable tmm-jet-text5 false)
  (.setBounds tmm-jet-label6 130 620 60 30)
  (.setBounds tmm-jet-text6 210 620 50 30)
  (.setEditable tmm-jet-text6 false)
  (.setBounds tmm-jet-label7 270 620 60 30)
  (.setBounds tmm-jet-text7 340 620 50 30)
  (.setEditable tmm-jet-text7 false)
  (.setBounds tmm-other-label 295 660 120 20)
  (.setBounds tmm-other-label1 110 690 30 30)
  (.setBounds tmm-other-text1 150 690 70 30)
  (.setEditable tmm-other-text1 false)
  (.setBounds tmm-other-label2 230 690 50 30)
  (.setBounds tmm-other-text2 290 690 70 30)
  (.setEditable tmm-other-text2 false)
  (.setBounds tmm-other-label3 380 690 60 30)
  (.setBounds tmm-other-text3 450 690 50 30)
  (.setEditable tmm-other-text3 false)
  (.setBounds tmm-other-text4 510 690 50 30)
  (.setEditable tmm-other-text4 false)
  (.setBackground check-tmm-btn (Color. 173 216 230))
  (.setBackground calc-tmm-button (Color. 173 216 230))
  (.setBackground reset-tmm-btn (Color. 173 216 230))
  (.setBackground tmm-btn (Color. 173 216 230))
  (.setBackground export-tmm (Color. 173 216 230))



(.addActionListener tmm-btn
                    (proxy [ActionListener] []
                      (actionPerformed [evt]
                        (.setVisible tmm-frame false)
                        (.setVisible nmm-frame true)
                        (doseq [fields (concat tmm-fields-zero tmm-fields-one tmm-fields-expr)]
                          (.setText fields "")
                          (when (or (some #(= fields %) tmm-fields-zero)
                                    (some #(= fields %) tmm-fields-one))
                            (.setEditable fields true)))
                        (.setEnabled check-tmm-btn true)
                        (.setEnabled reset-tmm-btn false)
                        (.setEnabled calc-tmm-button false))))

(.addActionListener check-tmm-btn
                    (proxy [ActionListener] []
                      (actionPerformed [evt]
                        (let [validation-result (proj/validate-fields tmm-fields-zero tmm-fields-one)]
                          (if (:valid validation-result)
                            (do
                              (.setEnabled calc-tmm-button true)
                              (.setEnabled check-tmm-btn false)
                              (doseq [field (concat tmm-fields-zero tmm-fields-one)]
                                (.setEditable field false)))
                            (do
                              (cond
                                (some #(<= % 0) (:values-zero validation-result))
                                (JOptionPane/showMessageDialog nil "All values (except kv, kps) must be greater than 0.")
                                (some #(not (proj/greater-than-one? % )) (:values-one validation-result))
                                (JOptionPane/showMessageDialog nil "Some values (kv,kps) must be greater than 1."))))))))

(.addActionListener reset-tmm-btn
                    (proxy [ActionListener] []
                      (actionPerformed [evt]
                        (doseq [field (concat tmm-fields-zero tmm-fields-one tmm-fields-expr)]
                          (.setText field "")
                          (when (or (some #(= field %) tmm-fields-zero)
                                    (some #(= field %) tmm-fields-one))
                            (.setEditable field true)))

                        (.setEnabled calc-tmm-button false)
                        (.setEnabled check-tmm-btn true)
                        (.setEnabled reset-tmm-btn false))))

(.addActionListener calc-tmm-button
                    (proxy [ActionListener] []
                      (actionPerformed [evt]
                        (.setEnabled reset-tmm-btn true)
                        (.setEnabled check-tmm-btn false)
                        (.setEnabled calc-tmm-button false)
                        (.setText tmm-exp1-text (format "%.3f" (proj/fn1 (Double/parseDouble (.getText tmm-par3-text)))))
                        (.setText tmm-exp2-text (format "%.3f" (proj/fn1 (Double/parseDouble (.getText tmm-par4-text)))))
                        (.setText tmm-exp3-text (format "%.3f" (proj/fn2 (Double/parseDouble (.getText tmm-par3-text)))))
                        (.setText tmm-exp4-text (format "%.3f" (proj/fn3 (Double/parseDouble (.getText tmm-par4-text)))))
                        (.setText tmm-exp5-text (format "%.3f" (proj/fn2 (Double/parseDouble (.getText tmm-par4-text)))))
                        (.setText tmm-exp6-text (format "%.3f" (proj/fn4 (Double/parseDouble (.getText tmm-par3-text)))))
                        (.setText tmm-par24-text (format "%.3f" (proj/T0 (Double/parseDouble (.getText tmm-par7-text)))))
                        (.setText tmm-par8-text (format "%.3f" (proj/a0 (Double/parseDouble(.getText tmm-par3-text)) (Double/parseDouble (.getText tmm-par18-text)) (Double/parseDouble (.getText tmm-par24-text)))))
                        (.setText tmm-par25-text (format "%.3f" (proj/ro0 (Double/parseDouble (.getText tmm-par7-text)))))
                        (.setText tmm-par26-text (format "%.3f" (proj/p0 (Double/parseDouble (.getText tmm-par7-text)))))
                        (.setText tmm-inlet-text1 (format "%.3f" (proj/p0* (Double/parseDouble(.getText tmm-par26-text)) (Double/parseDouble (.getText tmm-par3-text)) (Double/parseDouble (.getText tmm-par9-text)))))
                        (.setText tmm-inlet-text2 (format "%.3f" (proj/multiplication (Double/parseDouble(.getText tmm-inlet-text1)) (Double/parseDouble (.getText tmm-par10-text)))))
                        (.setText tmm-inlet-text4 (format "%.3f" (proj/division (Double/parseDouble(.getText tmm-inlet-text2)) (Double/parseDouble (.getText tmm-par26-text)))))
                        (.setText tmm-inlet-text5 (format "%.3f" (proj/tauu (Double/parseDouble(.getText tmm-par3-text)) (Double/parseDouble (.getText tmm-par9-text)))))
                        (.setText tmm-inlet-text3 (format "%.3f" (proj/multiplication (Double/parseDouble(.getText tmm-inlet-text5)) (Double/parseDouble (.getText tmm-par24-text)))))
                        (.setText tmm-par5-text (format "%.3f" (proj/multiplication (Double/parseDouble(.getText tmm-par9-text)) (Double/parseDouble (.getText tmm-par8-text)))))
                        (.setText tmm-compressor-text1 (format "%.3f" (proj/t2-tmm (Double/parseDouble(.getText tmm-inlet-text3)) (Double/parseDouble (.getText tmm-par27-text))  (Double/parseDouble (.getText tmm-par3-text))  (Double/parseDouble (.getText tmm-par11-text)))))
                        (.setText tmm-compressor-text2 (format "%.3f" (proj/multiplication(Double/parseDouble(.getText tmm-inlet-text2)) (Double/parseDouble (.getText tmm-par27-text)))))
                        (.setText tmm-compressor-text3 (format "%.3f" (proj/division(Double/parseDouble(.getText tmm-compressor-text1)) (Double/parseDouble (.getText tmm-inlet-text3)))))
                        (.setText tmm-compressor-text4 (format "%.3f" (proj/multiplication(Double/parseDouble(.getText tmm-compressor-text3)) (Double/parseDouble (.getText tmm-inlet-text5)))))
                        (.setText tmm-compressor-text5 (format "%.3f" (proj/multiplication(Double/parseDouble(.getText tmm-par27-text)) (Double/parseDouble (.getText tmm-inlet-text4)))))
                        (.setText tmm-chamber-text1 (format "%.3f" (proj/multiplication(Double/parseDouble(.getText tmm-compressor-text2)) (Double/parseDouble (.getText tmm-par12-text)))))
                        (.setText tmm-chamber-text2 (format "%.3f" (proj/tmm-mixing-ratio (Double/parseDouble(.getText tmm-par2-text)) (Double/parseDouble (.getText tmm-par29-text)) (Double/parseDouble(.getText tmm-par1-text)) (Double/parseDouble (.getText tmm-compressor-text1)) (Double/parseDouble(.getText tmm-par13-text)) (Double/parseDouble (.getText tmm-par17-text)))))
                        (.setText tmm-chamber-text3 (format "%.3f" (proj/division(Double/parseDouble(.getText tmm-par29-text)) (Double/parseDouble (.getText tmm-par24-text)))))
                        (.setText tmm-turbine-text5 (format "%.3f" (proj/wk(Double/parseDouble(.getText tmm-par1-text)) (Double/parseDouble (.getText tmm-compressor-text1)) (Double/parseDouble (.getText tmm-inlet-text3)))))
                        (.setText tmm-turbine-text6 (format "%.3f" (proj/wt(Double/parseDouble(.getText tmm-par6-text)) (Double/parseDouble (.getText tmm-turbine-text5)) (Double/parseDouble(.getText tmm-chamber-text2)) (Double/parseDouble(.getText tmm-par28-text)) (Double/parseDouble(.getText tmm-par15-text)))))
                        (.setText tmm-turbine-text3 (format "%.3f" (proj/tmm-t4(Double/parseDouble(.getText tmm-par29-text)) (Double/parseDouble(.getText tmm-turbine-text6)) (Double/parseDouble(.getText tmm-par2-text)))))
                        (.setText tmm-turbine-text4 (format "%.3f" (proj/division(Double/parseDouble(.getText tmm-par29-text)) (Double/parseDouble(.getText tmm-turbine-text3)))))
                        (.setText tmm-turbine-text1 (format "%.3f" (proj/pi-turbine  (Double/parseDouble(.getText tmm-turbine-text3)) (Double/parseDouble(.getText tmm-par29-text)) (Double/parseDouble(.getText tmm-par14-text)) (Double/parseDouble(.getText tmm-par4-text)))))
                        (.setText tmm-turbine-text2 (format "%.3f" (proj/division(Double/parseDouble(.getText tmm-chamber-text1)) (Double/parseDouble(.getText tmm-turbine-text1)))))
                        (.setText tmm-jet-text1 (format "%.3f" (proj/division(Double/parseDouble(.getText tmm-turbine-text2)) (Double/parseDouble(.getText tmm-par26-text)))))
                        (.setText tmm-jet-text2 (format "%.3f" (proj/conv-pim(Double/parseDouble(.getText tmm-jet-text1)) (Double/parseDouble(.getText tmm-par20-text)))))
                        (.setText tmm-jet-text3 (format "%.3f" (proj/tmm-vi(Double/parseDouble(.getText tmm-par16-text)) (Double/parseDouble(.getText tmm-par2-text)) (Double/parseDouble(.getText tmm-turbine-text3)) (Double/parseDouble(.getText tmm-jet-text2)) (Double/parseDouble(.getText tmm-par4-text)))))
                        (.setText tmm-jet-text4 (format "%.3f" (proj/mps (Double/parseDouble(.getText tmm-par6-text)) (Double/parseDouble(.getText tmm-chamber-text2)) (Double/parseDouble(.getText tmm-par28-text)))))
                        (.setText tmm-jet-text5 (format "%.3f" (proj/division(Double/parseDouble(.getText tmm-turbine-text2)) (Double/parseDouble(.getText tmm-jet-text2)))))
                        (.setText tmm-jet-text6 (format "%.3f" (proj/func-M (Double/parseDouble(.getText tmm-par9-text)) (Double/parseDouble(.getText tmm-par4-text)))))
                        (.setText tmm-jet-text7 (format "%.3f" (proj/out-a (Double/parseDouble(.getText tmm-jet-text4)) (Double/parseDouble(.getText tmm-jet-text6)) (Double/parseDouble(.getText tmm-par19-text)) (Double/parseDouble(.getText tmm-par4-text)) (Double/parseDouble(.getText tmm-turbine-text3)) (Double/parseDouble(.getText tmm-turbine-text2)))))
                        (.setText tmm-other-text1 (format "%.3f" (proj/tmm-thrust(Double/parseDouble(.getText tmm-par6-text)) (Double/parseDouble(.getText tmm-chamber-text2)) (Double/parseDouble(.getText tmm-par28-text)) (Double/parseDouble(.getText tmm-jet-text3)) (Double/parseDouble(.getText tmm-par5-text)) (Double/parseDouble(.getText tmm-jet-text7)) (Double/parseDouble(.getText tmm-jet-text5)) (Double/parseDouble(.getText tmm-par26-text)))))
                        (.setText tmm-other-text2 (format "%.3f" (proj/division(Double/parseDouble(.getText tmm-other-text1)) (Double/parseDouble(.getText tmm-par6-text)))))
                        (.setText tmm-other-text3 (format "%.5f" (proj/tmm-csps (Double/parseDouble(.getText tmm-chamber-text2)) (Double/parseDouble(.getText tmm-par6-text)) (Double/parseDouble(.getText tmm-other-text1)))))
                        (.setText tmm-other-text4 (format "%.3f" (proj/multiplication(Double/parseDouble(.getText tmm-other-text3)) 36000)))
                        )))

  (doto login-frame
    (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
    (.setSize 300 200)
    (.setResizable false)
    (.setVisible true)
    (.setLocationRelativeTo nil)
    (.add username-label)
    (.add username-text)
    (.add password-label)
    (.add password-text)
    (.add login-button))

  (doto accounts-frame
    (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
    (.setSize 500 500)
    (.setResizable false)
    (.setVisible false)
    (.setLocationRelativeTo nil)
    (.add username-acc-text)
    (.add password-acc-text)
    (.add account-type-combo)
    (.add add-button)
    (.add delete-button)
    (.add update-button)
    (.add scroll-pane))

  (doto main-frame
    (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
    (.setSize 330 87)
    (.setResizable false)
    (.setLocationRelativeTo nil)
    (.setVisible false)
    (.add main-nmm-button)
    (.add main-tmm-button))

  (doto nmm-frame
    (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
    (.setSize 450 850)
    (.setResizable false)
    (.setLocationRelativeTo nil)
    (.setVisible false))

  (doseq [component (concat fields-one fields-zero fields-expr labels-expr labels-one labels-zero btns)]
    (.add nmm-frame component))

  (doto tmm-frame
    (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
    (.setSize 700 780)
    (.setResizable false)
    (.setLocationRelativeTo nil)
    (.setVisible false))

  (doseq [components (concat tmm-fields-one tmm-fields-zero tmm-fields-expr tmm-labels-expr tmm-labels-one tmm-labels-zero tmm-btns)]
    (.add tmm-frame components)))