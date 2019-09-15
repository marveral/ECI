package eci.practica;

import jfi.events.PixelEvent;
import jfi.events.PixelListener;
import jfi.iu.ImageInternalFrame;
import java.awt.Color;
import java.awt.List;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Kernel;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.ml.Ml;
import org.opencv.ml.SVM;
import org.opencv.objdetect.HOGDescriptor;


/**
 * Ventana principal de la aplicación
 * 
 * @author Jesús Chamorro Martínez (jesus@decsai.ugr.es)
 */
public class ECIFrame extends javax.swing.JFrame {
    private String descriptorName;
    /**
     * Crea una ventana principal
     */
    public ECIFrame() {
        initComponents();
        this.descriptorName = "HoG";
        setIconImage((new ImageIcon(getClass().getResource("/icons/iconoJMR.png"))).getImage());
    }
    
    /**
     * Devuelve la ventana interna seleccionada de tipo imagen (null si no hubiese 
     * ninguna selecionada o si fuese de otro tipo) 
     * 
     * @return la ventana interna seleccionada de tipo imagen
     */
    public ImageInternalFrame getSelectedImageFrame() {
        JInternalFrame vi = escritorio.getSelectedFrame();
        if(vi instanceof ImageInternalFrame)
            return (ImageInternalFrame)escritorio.getSelectedFrame();
        else
            return null;
    }
    
    /**
     * Devuelve la imagen de la ventana interna selecionada
     * 
     * @return la imagen seleccionada
     */
    private BufferedImage getSelectedImage(){
        BufferedImage img = null;
        ImageInternalFrame vi = this.getSelectedImageFrame();
        if (vi != null) {
            if (vi.getType() == ImageInternalFrame.TYPE_STANDAR) {
                img = vi.getImage();
            } 
            else {
                JOptionPane.showInternalMessageDialog(escritorio, "An image must be selected", "Image", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        return img;
    }
        
    /**
     * Devuelve el título de la ventana interna selecionada
     * 
     * @return el título de la ventana interna selecionada
     */
    private String getSelectedFrameTitle(){
        String title = "";
        JInternalFrame vi = escritorio.getSelectedFrame();
        if (vi != null) {
            title = vi.getTitle();
        }
        return title;
    }
    
    /**
     * Sitúa la ventana interna <tt>vi</tt> debajo de la ventana interna activa 
     * y con el mismo tamaño.
     * 
     * @param vi la ventana interna
     */
    private void locateInternalFrame(JInternalFrame vi) {
        JInternalFrame vSel = escritorio.getSelectedFrame();
        if (vSel != null) {
            vi.setLocation(vSel.getX() + 20, vSel.getY() + 20);
            vi.setSize(vSel.getSize());
        }
    }
    
    /**
     * Muestra la ventana interna <tt>vi</tt> 
     * 
     * @param vi la ventana interna
     */
    private void showInternalFrame(JInternalFrame vi) {        
        if(vi instanceof ImageInternalFrame){
            ((ImageInternalFrame)vi).setGrid(this.verGrid.isSelected());
            ((ImageInternalFrame)vi).addPixelListener(new ManejadorPixel());
        }
        this.locateInternalFrame(vi);
        this.escritorio.add(vi);
        vi.setVisible(true);
    }  
    
    /**
     * Clase interna manejadora de eventos de pixel
     */
    private class ManejadorPixel implements PixelListener {
        /**
         * Gestiona el cambio de localización del pixel activo, actualizando
         * la información de la barra de tareas.
         * 
         * @param evt evento de pixel
         */
        @Override
        public void positionChange(PixelEvent evt) {
            String text = " ";
            Point p = evt.getPixelLocation();
            if (p != null) {
                Color c = evt.getRGB();
                Integer alpha = evt.getAlpha();
                text = "(" + p.x + "," + p.y + ") : [" + c.getRed() + "," + c.getGreen() + "," + c.getBlue();
                text += alpha == null ? "]" : ("," + alpha + "]");
            }
            posicionPixel.setText(text);
        }
    } 
    
    /*
     * Código generado por Netbeans para el diseño del interfaz
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenuPanelOutput = new javax.swing.JPopupMenu();
        clear = new javax.swing.JMenuItem();
        popupMenuSeleccionDescriptores = new javax.swing.JPopupMenu();
        colorDominante = new javax.swing.JRadioButtonMenuItem();
        colorEstructurado = new javax.swing.JRadioButtonMenuItem();
        colorEscalable = new javax.swing.JRadioButtonMenuItem();
        colorMedio = new javax.swing.JRadioButtonMenuItem();
        separadorDescriptores = new javax.swing.JPopupMenu.Separator();
        texturaHomogeneidad = new javax.swing.JRadioButtonMenuItem();
        texturaEdge = new javax.swing.JRadioButtonMenuItem();
        popupMenuGrid = new javax.swing.JPopupMenu();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        popupMenuSeleccionDescriptoresDB = new javax.swing.JPopupMenu();
        colorDominanteDB = new javax.swing.JRadioButtonMenuItem();
        colorEstructuradoDB = new javax.swing.JRadioButtonMenuItem();
        colorEscalableDB = new javax.swing.JRadioButtonMenuItem();
        colorMedioDB = new javax.swing.JRadioButtonMenuItem();
        separadorDescriptoresDB = new javax.swing.JPopupMenu.Separator();
        texturaHomogeneidadDB = new javax.swing.JRadioButtonMenuItem();
        texturaEdgeDB = new javax.swing.JRadioButtonMenuItem();
        splitPanelCentral = new javax.swing.JSplitPane();
        escritorio = new javax.swing.JDesktopPane();
        showPanelInfo = new javax.swing.JLabel();
        panelTabuladoInfo = new javax.swing.JTabbedPane();
        panelOutput = new javax.swing.JPanel();
        scrollEditorOutput = new javax.swing.JScrollPane();
        editorOutput = new javax.swing.JEditorPane();
        panelBarraHerramientas = new javax.swing.JPanel();
        barraArchivo = new javax.swing.JToolBar();
        botonAbrir = new javax.swing.JButton();
        botonGuardar = new javax.swing.JButton();
        barraImagen = new javax.swing.JToolBar();
        botonCanny = new javax.swing.JButton();
        botonHarris = new javax.swing.JButton();
        botonTrain = new javax.swing.JButton();
        botonTest = new javax.swing.JButton();
        botonBondad = new javax.swing.JButton();
        botonParametros = new javax.swing.JButton();
        tipoDescriptor = new javax.swing.JComboBox<>();
        barraEstado = new javax.swing.JPanel();
        posicionPixel = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        menuAbrir = new javax.swing.JMenuItem();
        menuGuardar = new javax.swing.JMenuItem();
        separador1 = new javax.swing.JPopupMenu.Separator();
        closeAll = new javax.swing.JMenuItem();
        menuVer = new javax.swing.JMenu();
        verGrid = new javax.swing.JCheckBoxMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menuZoom = new javax.swing.JMenu();
        menuZoomIn = new javax.swing.JMenuItem();
        menuZoomOut = new javax.swing.JMenuItem();

        popupMenuPanelOutput.setAlignmentY(0.0F);
        popupMenuPanelOutput.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        popupMenuPanelOutput.add(clear);

        colorDominante.setText("Dominant color");
        popupMenuSeleccionDescriptores.add(colorDominante);

        colorEstructurado.setSelected(true);
        colorEstructurado.setText("Structured color");
        popupMenuSeleccionDescriptores.add(colorEstructurado);

        colorEscalable.setText("Scalable color");
        popupMenuSeleccionDescriptores.add(colorEscalable);

        colorMedio.setText("Mean color");
        popupMenuSeleccionDescriptores.add(colorMedio);
        popupMenuSeleccionDescriptores.add(separadorDescriptores);

        texturaHomogeneidad.setText("Homogeneous texture");
        texturaHomogeneidad.setEnabled(false);
        popupMenuSeleccionDescriptores.add(texturaHomogeneidad);

        texturaEdge.setText("Edge histogram");
        texturaEdge.setEnabled(false);
        popupMenuSeleccionDescriptores.add(texturaEdge);

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");
        popupMenuGrid.add(jRadioButtonMenuItem1);

        colorDominanteDB.setText("Dominant color");
        colorDominanteDB.setEnabled(false);
        popupMenuSeleccionDescriptoresDB.add(colorDominanteDB);

        colorEstructuradoDB.setText("Structured color");
        popupMenuSeleccionDescriptoresDB.add(colorEstructuradoDB);

        colorEscalableDB.setText("Scalable color");
        popupMenuSeleccionDescriptoresDB.add(colorEscalableDB);

        colorMedioDB.setSelected(true);
        colorMedioDB.setText("Mean color");
        popupMenuSeleccionDescriptoresDB.add(colorMedioDB);
        popupMenuSeleccionDescriptoresDB.add(separadorDescriptoresDB);

        texturaHomogeneidadDB.setText("Homogeneous texture");
        texturaHomogeneidadDB.setEnabled(false);
        popupMenuSeleccionDescriptoresDB.add(texturaHomogeneidadDB);

        texturaEdgeDB.setText("Edge histogram");
        texturaEdgeDB.setEnabled(false);
        popupMenuSeleccionDescriptoresDB.add(texturaEdgeDB);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Práctica Extracción de Rasgos");
        setName("ventanaPrincipal"); // NOI18N

        splitPanelCentral.setDividerLocation(1.0);
        splitPanelCentral.setDividerSize(3);
        splitPanelCentral.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        splitPanelCentral.setPreferredSize(new java.awt.Dimension(0, 0));
        splitPanelCentral.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                splitPanelCentralPropertyChange(evt);
            }
        });

        escritorio.setBackground(java.awt.Color.lightGray);
        escritorio.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        showPanelInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/desplegar20.png"))); // NOI18N
        showPanelInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                showPanelInfoMousePressed(evt);
            }
        });

        escritorio.setLayer(showPanelInfo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, escritorioLayout.createSequentialGroup()
                .addGap(0, 1019, Short.MAX_VALUE)
                .addComponent(showPanelInfo))
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, escritorioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(showPanelInfo))
        );

        splitPanelCentral.setTopComponent(escritorio);

        panelTabuladoInfo.setMinimumSize(new java.awt.Dimension(0, 0));
        panelTabuladoInfo.setPreferredSize(new java.awt.Dimension(0, 0));

        panelOutput.setMinimumSize(new java.awt.Dimension(0, 0));
        panelOutput.setPreferredSize(new java.awt.Dimension(0, 0));
        panelOutput.setLayout(new java.awt.BorderLayout());

        scrollEditorOutput.setMinimumSize(new java.awt.Dimension(0, 0));

        editorOutput.setMinimumSize(new java.awt.Dimension(0, 0));
        editorOutput.setPreferredSize(new java.awt.Dimension(0, 0));
        editorOutput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                editorOutputMouseReleased(evt);
            }
        });
        scrollEditorOutput.setViewportView(editorOutput);

        panelOutput.add(scrollEditorOutput, java.awt.BorderLayout.CENTER);

        panelTabuladoInfo.addTab("Output", panelOutput);

        splitPanelCentral.setBottomComponent(panelTabuladoInfo);

        getContentPane().add(splitPanelCentral, java.awt.BorderLayout.CENTER);

        panelBarraHerramientas.setAlignmentX(0.0F);
        panelBarraHerramientas.setAlignmentY(0.0F);
        panelBarraHerramientas.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        barraArchivo.setRollover(true);
        barraArchivo.setAlignmentX(0.0F);

        botonAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/open24.png"))); // NOI18N
        botonAbrir.setToolTipText("Open");
        botonAbrir.setFocusable(false);
        botonAbrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonAbrir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAbrirActionPerformed(evt);
            }
        });
        barraArchivo.add(botonAbrir);

        botonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save24.png"))); // NOI18N
        botonGuardar.setToolTipText("Save");
        botonGuardar.setFocusable(false);
        botonGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });
        barraArchivo.add(botonGuardar);

        panelBarraHerramientas.add(barraArchivo);

        barraImagen.setRollover(true);

        botonCanny.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/blur.png"))); // NOI18N
        botonCanny.setToolTipText("Test");
        botonCanny.setFocusable(false);
        botonCanny.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonCanny.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonCanny.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCannyActionPerformed(evt);
            }
        });
        barraImagen.add(botonCanny);

        botonHarris.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/grupo.png"))); // NOI18N
        botonHarris.setToolTipText("Detector de esquinas de Harris");
        botonHarris.setFocusable(false);
        botonHarris.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonHarris.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonHarris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonHarrisActionPerformed(evt);
            }
        });
        barraImagen.add(botonHarris);

        botonTrain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ecualiza24.png"))); // NOI18N
        botonTrain.setToolTipText("Entrena clasificador");
        botonTrain.setFocusable(false);
        botonTrain.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonTrain.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonTrain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonTrainActionPerformed(evt);
            }
        });
        barraImagen.add(botonTrain);

        botonTest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/test24.png"))); // NOI18N
        botonTest.setMnemonic('C');
        botonTest.setToolTipText("Realiza una predicción");
        botonTest.setEnabled(false);
        botonTest.setFocusable(false);
        botonTest.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonTest.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonTestActionPerformed(evt);
            }
        });
        barraImagen.add(botonTest);

        panelBarraHerramientas.add(barraImagen);

        botonBondad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/effective.png"))); // NOI18N
        botonBondad.setToolTipText("Bondad");
        botonBondad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBondadActionPerformed(evt);
            }
        });
        panelBarraHerramientas.add(botonBondad);

        botonParametros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/training.png"))); // NOI18N
        botonParametros.setToolTipText("Parametros");
        botonParametros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonParametrosActionPerformed(evt);
            }
        });
        panelBarraHerramientas.add(botonParametros);

        tipoDescriptor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HoG", "LBP", "HoG+LBP" }));
        tipoDescriptor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoDescriptorActionPerformed(evt);
            }
        });
        panelBarraHerramientas.add(tipoDescriptor);

        getContentPane().add(panelBarraHerramientas, java.awt.BorderLayout.PAGE_START);

        barraEstado.setLayout(new java.awt.BorderLayout());

        posicionPixel.setText("  ");
        barraEstado.add(posicionPixel, java.awt.BorderLayout.LINE_START);

        getContentPane().add(barraEstado, java.awt.BorderLayout.SOUTH);

        menuArchivo.setText("File");

        menuAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/open16.png"))); // NOI18N
        menuAbrir.setText("Open");
        menuAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAbrirActionPerformed(evt);
            }
        });
        menuArchivo.add(menuAbrir);

        menuGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save16.png"))); // NOI18N
        menuGuardar.setText("Save");
        menuGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGuardarActionPerformed(evt);
            }
        });
        menuArchivo.add(menuGuardar);
        menuArchivo.add(separador1);

        closeAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/closeall16.png"))); // NOI18N
        closeAll.setText("Close all");
        closeAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeAllActionPerformed(evt);
            }
        });
        menuArchivo.add(closeAll);

        menuBar.add(menuArchivo);

        menuVer.setText("View");

        verGrid.setSelected(true);
        verGrid.setText("Show grid");
        verGrid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verGridActionPerformed(evt);
            }
        });
        menuVer.add(verGrid);
        menuVer.add(jSeparator2);

        menuZoom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/zoom16.png"))); // NOI18N
        menuZoom.setText("Zoom");

        menuZoomIn.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_PLUS, 0));
        menuZoomIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/zoom-in16.png"))); // NOI18N
        menuZoomIn.setText("Zoom in");
        menuZoomIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuZoomInActionPerformed(evt);
            }
        });
        menuZoom.add(menuZoomIn);

        menuZoomOut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_MINUS, 0));
        menuZoomOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/zoom-out16.png"))); // NOI18N
        menuZoomOut.setText("Zoom out");
        menuZoomOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuZoomOutActionPerformed(evt);
            }
        });
        menuZoom.add(menuZoomOut);

        menuVer.add(menuZoom);

        menuBar.add(menuVer);

        setJMenuBar(menuBar);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAbrirActionPerformed
        BufferedImage img;
        JFileChooser dlg = new JFileChooser();
        dlg.setMultiSelectionEnabled(true);
        int resp = dlg.showOpenDialog(this);
        if (resp == JFileChooser.APPROVE_OPTION) {
            try {                
                File files[] = dlg.getSelectedFiles();              
                for (File f : files) {
                    img = ImageIO.read(f);
                    if (img != null) {
                        ImageInternalFrame vi = new ImageInternalFrame(this, img);
                        vi.setTitle(f.getName());
                        this.showInternalFrame(vi);
                    }
                }               
            } catch (Exception ex) {
                JOptionPane.showInternalMessageDialog(escritorio, "Error in image opening", "Image", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_menuAbrirActionPerformed

    private void menuGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGuardarActionPerformed
        BufferedImage img = this.getSelectedImage();
        if (img != null) {
            JFileChooser dlg = new JFileChooser();
            int resp = dlg.showSaveDialog(this);
            if (resp == JFileChooser.APPROVE_OPTION) {
                File f = dlg.getSelectedFile();
                try {
                    ImageIO.write(img, "png", f);
                    escritorio.getSelectedFrame().setTitle(f.getName());
                } catch (Exception ex) {
                    JOptionPane.showInternalMessageDialog(escritorio, "Error in image saving", "Image", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }      
    }//GEN-LAST:event_menuGuardarActionPerformed

    private void botonAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAbrirActionPerformed
        this.menuAbrirActionPerformed(evt);
    }//GEN-LAST:event_botonAbrirActionPerformed

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        this.menuGuardarActionPerformed(evt);
    }//GEN-LAST:event_botonGuardarActionPerformed
    
    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        this.editorOutput.setText("");
    }//GEN-LAST:event_clearActionPerformed

    private void splitPanelCentralPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_splitPanelCentralPropertyChange
        if (evt.getPropertyName().equals("dividerLocation")) {
            float dividerLocation = (float) splitPanelCentral.getDividerLocation() / splitPanelCentral.getMaximumDividerLocation();
            if (dividerLocation >= 1) {//Está colapsada
                showPanelInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/desplegar20.png")));
            } else {
                showPanelInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cerrar16.png")));
            }
        }
    }//GEN-LAST:event_splitPanelCentralPropertyChange

    private void editorOutputMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editorOutputMouseReleased
        if(evt.isPopupTrigger()){
            Point p = this.scrollEditorOutput.getMousePosition();
            this.popupMenuPanelOutput.show(this.panelOutput,p.x,p.y);
        }
    }//GEN-LAST:event_editorOutputMouseReleased

    private void showPanelInfoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showPanelInfoMousePressed
        float dividerLocation = (float)splitPanelCentral.getDividerLocation()/splitPanelCentral.getMaximumDividerLocation();
        if(dividerLocation>=1) {//Está colapsada
            splitPanelCentral.setDividerLocation(0.8);
        } else{
            splitPanelCentral.setDividerLocation(1.0);
        }
    }//GEN-LAST:event_showPanelInfoMousePressed

    private void closeAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeAllActionPerformed
       escritorio.removeAll();
       escritorio.repaint();
    }//GEN-LAST:event_closeAllActionPerformed

    private void menuZoomOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuZoomOutActionPerformed
        ImageInternalFrame vi = this.getSelectedImageFrame();
        if (vi != null) {
            int zoom = vi.getZoom();
            if(zoom>=2){
                vi.setZoom(zoom-1);
                vi.repaint();
            }
        }
    }//GEN-LAST:event_menuZoomOutActionPerformed

    private void menuZoomInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuZoomInActionPerformed
        ImageInternalFrame vi = this.getSelectedImageFrame();
        if (vi != null) {
            vi.setZoom(vi.getZoom()+1);
            vi.repaint();
        }
    }//GEN-LAST:event_menuZoomInActionPerformed

    private void verGridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verGridActionPerformed
        JInternalFrame ventanas[] = escritorio.getAllFrames();
        for(JInternalFrame vi: ventanas){
            ((ImageInternalFrame)vi).setGrid(this.verGrid.isSelected());
            vi.repaint();
        }
    }//GEN-LAST:event_verGridActionPerformed

    /**
     * Convierte una imagen a una matriz openCV
     * @param img imagen a convertir
     * @return matriz con la imagen
     */
    private Mat fromImagetoMat(BufferedImage img) {
        Mat m_img = new Mat(img.getHeight(), img.getWidth(), CvType.CV_8UC3);
        byte[] pixels = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        m_img.put(0, 0, pixels);
        return m_img;
    }
    
    private void botonCannyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCannyActionPerformed
        BufferedImage img = this.getSelectedImage();
        if (img != null) {
            //Convertimos BufferedImage a matriz openCV
            Mat m_img = fromImagetoMat(img);

            //Operamos sobre la matriz
            Mat gris = new Mat(m_img.width(), m_img.height(), m_img.type());
            Mat blur = new Mat(m_img.width(), m_img.height(), m_img.type());
            Mat canny = new Mat(m_img.width(), m_img.height(), m_img.type());
            Imgproc.cvtColor(m_img, gris, Imgproc.COLOR_RGB2GRAY);
            Imgproc.blur(gris, blur, new Size(3, 3));
            Imgproc.Canny(blur, canny, 50, 150);
            
            //Convertimos matriz a BufferedImage
            BufferedImage img_output = (BufferedImage)HighGui.toBufferedImage(canny);
            if (img_output != null) {
                ImageInternalFrame vi = new ImageInternalFrame(this, img_output);
                vi.setTitle("Canny");
                this.showInternalFrame(vi);
            }
        }
    }//GEN-LAST:event_botonCannyActionPerformed

    private void botonHarrisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonHarrisActionPerformed
        BufferedImage img = this.getSelectedImage();
        if (img != null) {
            //Convertimos BufferedImage a matriz openCV
            Mat m_img = fromImagetoMat(img);

            //Operamos sobre la matriz
            Mat gris = new Mat(m_img.width(), m_img.height(), m_img.type());
            Imgproc.cvtColor(m_img, gris, Imgproc.COLOR_RGB2GRAY);
            Mat harris = new Mat(m_img.width(), m_img.height(), m_img.type());
            Imgproc.cornerHarris(gris, harris, 2, 3, 0.04);
            Core.normalize(harris, harris, 0, 255, Core.NORM_MINMAX);
            Core.convertScaleAbs(harris, harris);
            
            //Convertimos matriz a BufferedImage
            BufferedImage img_output = (BufferedImage)HighGui.toBufferedImage(harris);
            if (img_output != null) {
                ImageInternalFrame vi = new ImageInternalFrame(this, img_output);
                vi.setTitle("Canny");
                this.showInternalFrame(vi);
            }
        }
    }//GEN-LAST:event_botonHarrisActionPerformed
   
    private SVM clasificador;
    
    private void botonTrainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonTrainActionPerformed
        System.out.println(SVM.SIGMOID);
        setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        //Obtenemos los datos para el entrenamiento del clasificador
        Mat trainingData = new Mat();
        Mat classes = new Mat();
        this.loadData(trainingData, classes,"train");       
        //Entrenamos el clasificador
        clasificador = this.train(trainingData, classes);
        this.botonTest.setEnabled(true);
        this.botonTrain.setEnabled(false);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_botonTrainActionPerformed

    private void botonTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonTestActionPerformed
        if (clasificador != null) {
            BufferedImage img = this.getSelectedImage();
            if (img != null) {
                //Convertimos BufferedImage a matriz openCV
                Mat m_img = fromImagetoMat(img);
                //HOG de la imagen a testear
                MatOfFloat descriptor = new MatOfFloat();
                if(this.descriptorName == "HoG"){
                    HOGDescriptor hog = new HOGDescriptor();
                    hog.compute(m_img, descriptor);
                }
                else if(this.descriptorName == "LBP") LBP(m_img,descriptor);
                else {
                    HOGDescriptor hog = new HOGDescriptor();
                    hog.compute(m_img, descriptor);
                    MatOfFloat descriptor_aux = new MatOfFloat();
                    LBP(m_img,descriptor_aux);
                    descriptor.push_back(descriptor_aux);
                }
                //Clasificación
                String text = editorOutput.getText();
                try {
                    float prediccion = clasificador.predict(descriptor.reshape(1, 1));
                    text += "\nPredicción: " + (prediccion > 0.0 ? "Persona" : "Fondo");
                } catch (Exception ex) {
                    text += "\nNo se puede clasificar (imagen "+img.getWidth()+"x"+img.getHeight()+")";
                }
                editorOutput.setText(text);
            }
        }
    }//GEN-LAST:event_botonTestActionPerformed

    private void botonBondadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBondadActionPerformed
        // Cargamos los datos de train y test
        Mat data = new Mat();
        Mat classes = new Mat();
        this.loadData(data, classes,"train");
       
        
        Mat testData;
        Mat testClasses;
        Mat trainData;
        Mat trainClasses;
        //this.loadData(testData, testClasses,"test");
        
        String text = "";
        int total = data.rows();
        int numVal = 6; //Número de validaciones
        int testTam = total / numVal;
        int correctas_0; 
        int correctas_1;
        int num_test_0 = 0;
        
        ArrayList<Integer> indices = shuffleIndices(classes,total,numVal,testTam);
        
        ArrayList<Float> bondad = new ArrayList<Float>();
        for (int i = 0 ; i < 3; i++) bondad.add((float)0);
        
        ArrayList<Float> aux = new ArrayList<Float>();
        // aqui
        // Validación cruzada 
        for (int i = 0; i < numVal; i++){
            text = editorOutput.getText() + "\n\nIteración " + i;
            editorOutput.setText(text);
            
            correctas_0 = 0;
            correctas_1 = 0;
            num_test_0 = 0;
            testData = new Mat();
            testClasses = new Mat();
            trainData = new Mat();
            trainClasses = new Mat();
            
            // Test index in [ i*testTam, i*testTam + testTam]
            int menor = i*testTam;
            int mayor = i*testTam + testTam;
            
            // Creo los conjuntos de train y test
            text = editorOutput.getText() + "\nObteniendo conjuntos train y test...";
            editorOutput.setText(text);
            
            for ( int j = 0 ; j < total ; j++){
                if ( j < menor || j > mayor){ // train
                    trainData.push_back(data.row(indices.get(j)));
                    trainClasses.push_back(classes.row(indices.get(j)));
                }
                else{ 
                    testData.push_back(data.row(indices.get(j)));
                    testClasses.push_back(classes.row(indices.get(j)));
                }
            }
            // Entreno el modelo
            text = editorOutput.getText() + "\nEntrenando el modelo...";
            editorOutput.setText(text);
            
            clasificador = this.train(trainData,trainClasses);
            if (clasificador != null){ // Si no ha habido ningún error 
                text = editorOutput.getText() + "\nRealizando predicción";
                editorOutput.setText(text);
                
                float prediccion ; 
                for ( int k = 0 ; k < testTam ; k++){
                    prediccion = clasificador.predict(testData.row(k));
                    if ( prediccion == testClasses.get(k,0)[0]){
                        if (prediccion == 0.0) correctas_0 +=1;
                        else correctas_1 += 1;
                    }
                    if(testClasses.get(k,0)[0] == 0.0) num_test_0 += 1;
                }
                aux = getBondad(testTam,correctas_1,correctas_0,num_test_0);
                for(int m = 0 ; m < bondad.size() ; m++) bondad.set(m, bondad.get(m)+aux.get(m));
            }
            else{
                System.out.println("ERROR!!");
            }
        }
        text = editorOutput.getText();
        for(int m = 0 ; m < bondad.size() ; m++){
            System.out.println(bondad.get(m)/numVal);
            text += "\n";
            if(m==0) text+="Accuracy = ";
            else if(m==1) text+="TPR = ";
            else text+="TNR = ";
            text += bondad.get(m)/numVal;
        }
        editorOutput.setText(text);
    }//GEN-LAST:event_botonBondadActionPerformed

    private ArrayList<Integer> shuffleIndices(Mat classes,int total, int numVal, int testTam){
        ArrayList<Integer> indices_1 = new ArrayList<Integer>(); 
        ArrayList<Integer> indices_0 = new ArrayList<Integer>(); 
        ArrayList<Integer> indices = new ArrayList<Integer>();
        for (int i = 0; i < total ; ++i) {
            if (classes.get(i,0)[0] == 0.0) indices_0.add(i);
            else indices_1.add(i);
        }
        int num_0 = indices_0.size() / numVal;
        Collections.shuffle(indices_1);
        Collections.shuffle(indices_0); 
        for( int i = 0; i < numVal ; i++){
            for (int j = 0 ; j <= num_0 ; j++){
                if(indices_0.size() > 0){
                    indices.add(indices_0.get(0));
                    indices_0.remove(0);
                }
                
            }
            for (int j = num_0 ; j <= testTam; j++){
                if(indices_1.size() > 0){
                    indices.add(indices_1.get(0));
                    indices_1.remove(0);
                }
                
            }
        }
        return indices;
    }
    
    
    private void botonParametrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonParametrosActionPerformed
        // Cargamos los datos de train y test
        Mat data = new Mat();
        Mat classes = new Mat();
        this.loadData(data, classes,"train");
        
        Mat testData;
        Mat testClasses;
        Mat trainData;
        Mat trainClasses;
        //this.loadData(testData, testClasses,"test");
        
        String text = "";
        int total = data.rows();
        int numVal = 6; //Número de validaciones
        int testTam = total / numVal;
        int correctas_0;
        int correctas_1;
        int num_test_0;
        
        ArrayList<Integer> indices = shuffleIndices(classes,total,numVal,testTam);
        ArrayList<Float> bondad = new ArrayList<Float>();
        for (int i = 0 ; i < 3; i++) bondad.add((float)0);
        ArrayList<Float> aux = new ArrayList<Float>();
        ArrayList<Double> params = new ArrayList<Double>();
        
        for (int n = 100 ; n < 102; n++){ // 100 -> SVM.C_SVC; 101-> SVM.NU_SVR, 
            for ( int j = 0 ; j < 2 ; j = j++){ // Iter in kernels: 0-> LINEAL , 1-> POLINOMIAL
                params = new ArrayList<Double>();
                int p ;
                if( j == SVM.POLY ){
                    if( n == SVM.NU_SVC ){
                        for( p = 0 ; p < 4; p++) params.add(0.2);
                        for( p = 0 ; p < 4; p++) params.add(0.3);
                        for( p = 0 ; p < 4; p++) params.add(0.4);
                    }
                    else{
                        params.add(2.0);
                        params.add(3.0);
                        params.add(4.0);
                    }
                }
                else if( n == SVM.NU_SVC){
                    params.add(0.1);
                    params.add(0.2);
                    params.add(0.3);
                    params.add(0.4); //here
                }
                else{
                    params.add(0.0);
                }
                p = 0;
                double ant_param = 0;
                for( double param : params){
                    if(ant_param == param) p+=1;
                    else p = 1;
                    ant_param = param;
                    
                    System.out.println("\n\n");
                    System.out.println("\nTraining using: \n\tKernel: "+j+"\n\tType: "+n + 
                            "\n\tParam: "+ param);
                    text = editorOutput.getText() + "\nTraining using: \n\tKernel: "+j+"\n\tType: "+n + 
                            "\n\tParam: "+ param + p;
                    editorOutput.setText(text);
                    
                    for (int i = 0; i < numVal; i++){ // Validación cruzada
                        text = editorOutput.getText() + "\n\nIteración " + i;
                        editorOutput.setText(text);

                        correctas_0 = 0;
                        correctas_1 = 0;
                        num_test_0 = 0 ;
                        testData = new Mat();
                        testClasses = new Mat();
                        trainData = new Mat();
                        trainClasses = new Mat();

                        // Test index in [ i*testTam, i*testTam + testTam]
                        int menor = i*testTam;
                        int mayor = i*testTam + testTam;

                        // Creo los conjuntos de train y test
                        text = editorOutput.getText() + "\nObteniendo conjuntos train y test...";
                        editorOutput.setText(text);

                        for ( int k = 0 ; k < total ; k++){
                            if ( k < menor || k > mayor){ // train
                                trainData.push_back(data.row(indices.get(k)));
                                trainClasses.push_back(classes.row(indices.get(k)));
                            }
                            else{ 
                                testData.push_back(data.row(indices.get(k)));
                                testClasses.push_back(classes.row(indices.get(k)));
                            }
                        }

                        // Entreno el modelo
                        text = editorOutput.getText() + "\nEntrenando el modelo...";
                        editorOutput.setText(text);
            
                        this.clasificador = trainSetParams(trainData,trainClasses,j,n,param,p);
                        if (clasificador != null){ // Si no ha habido ningún error 
                            text = editorOutput.getText() + "\nRealizando predicción";
                            editorOutput.setText(text);

                            float prediccion ; 
                            for ( int k = 0 ; k < testTam ; k++){
                                prediccion = clasificador.predict(testData.row(k));
                                if ( prediccion == testClasses.get(k,0)[0]){
                                    if (prediccion == 0.0) correctas_0 +=1;
                                    else correctas_1 += 1;
                                }
                                if(testClasses.get(k,0)[0] == 0.0) num_test_0 += 1;
                            }
                            aux = getBondad(testTam,correctas_1,correctas_0,num_test_0);
                            for(int m = 0 ; m < bondad.size() ; m++) bondad.set(m, bondad.get(m)+aux.get(m));
                        }
                        else{
                            System.out.println("ERROR!!");
                        }
                    }
                    System.out.println("BONDAD: ");
                    text = editorOutput.getText();
                    for(int m = 0 ; m < bondad.size() ; m++){
                        System.out.println(bondad.get(m)/numVal);
                        text += "\n";
                        if(m==0) text+="Accuracy = ";
                        else if(m==1) text+="TPR = ";
                        else text+="TNR = ";
                        text += bondad.get(m)/numVal;
                    }
                    for(int m = 0 ; m < bondad.size() ; m++) bondad.set(m,(float)0);
                    editorOutput.setText(text);
                }       
            }
        }
    }//GEN-LAST:event_botonParametrosActionPerformed

    private void tipoDescriptorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoDescriptorActionPerformed
        this.descriptorName = this.tipoDescriptor.getSelectedItem().toString();
    }//GEN-LAST:event_tipoDescriptorActionPerformed
    
    /**
     * Muestra por pantalla medidas relativas a la bondad del clasifiador
     * @param total número total de predicciones realizadas
     * @param correctas_1 número de elementos de la clase 1 predichos correctamente
     * @param correctas_0 número de elementos de la clase 0 predichos correctamente
     * @param num_class_0 número de elementos de la clase 0 en el conjunto test
     */
    private ArrayList<Float> getBondad(int total, int correctas_1,int correctas_0, int num_class_0){
        ArrayList<Float> bondad = new ArrayList<Float>();
        bondad.add((float)(correctas_1 + correctas_0) / total);
        bondad.add((float) correctas_1 / (total - num_class_0));
        bondad.add((float) correctas_0 / num_class_0);
        
        return bondad;
    }
    /**
     * Entrena el clasificador.
     * 
     * @param trainingData datos de entrenamiento
     * @param classes clases asocidas a los datos de entrenamiento
     * @return un clasificador SVM
     */
    private SVM train(Mat trainingData, Mat classes) {
        SVM svm = SVM.create();
        svm.setKernel(SVM.SIGMOID); // SVM.LINEAR, SVM.POLY, radial basis 
        //svm.setDegree(2);
        svm.setGamma(1);
        svm.setType(SVM.C_SVC); // SVM.C_SVC, SVM.NU_SVC, SVM.EPS_SVR,SVM.NU_SVR,SVM.ONE_CLASS
        svm.train(trainingData, Ml.ROW_SAMPLE, classes);
        return svm;
    }
    
    /**
     * Entrena el clasificador especificando los párametros a utilizas
     * 
     * @param trainingData datos de entrenamiento
     * @param classes clases asocidas a los datos de entrenamiento
     * @param kernel kenel de SVM
     * @param type type a usar en SVM
     * @return un clasificador SVM
     */
    private SVM trainSetParams(Mat trainingData, Mat classes, int kernel, int type,double param,int iter) {              
        SVM svm = SVM.create();
        svm.setKernel(kernel);           // SVM.LINEAR, SVM.POLY, radial basis  -> 0, 1
        svm.setType(type);
        if ( kernel == SVM.POLY) svm.setDegree(param);
        
        if ( type == SVM.NU_SVC){
            if (kernel == SVM.POLY) { svm.setNu((double) 0.1 * iter); }
            svm.setNu(param);
        }

        svm.train(trainingData, Ml.ROW_SAMPLE, classes);
        return svm;
    }
    
    
    private void loadTrainingData(Mat trainingData, Mat classes) {
        loadData(trainingData,classes,"train");
    }
    
    
    /**
     * Calcula el histograma de una lista
     * @param lista lista sobre la que calcular el histograma
     * @return histograma
     */
    private ArrayList<Float> getHistograma(ArrayList<Integer> lista){
        ArrayList<Float> histograma = new ArrayList<Float>();
        for(int i = 0 ; i < 256;i++) histograma.add((float)0);
        for(int i = 0 ; i <  lista.size(); i++){
            histograma.set(lista.get(i), histograma.get(lista.get(i))+1);
        }
        return histograma;
    }
    
    /**
     * Implementa LBP
     * @param img
     * @param descriptor 
     */
    private void LBP(Mat img, MatOfFloat descriptor){
        int num_rows = img.rows();
        int num_cols = img.cols();
        ArrayList<Integer> results = new ArrayList<Integer>();
        ArrayList<Float>  histogramas = new ArrayList<Float>();
        ArrayList<Float>  aux = new ArrayList<Float>();
        
        // Itero sobre los pixeles
        int num_bloques_row = num_rows/8; // 16
        int num_bloques_col = num_cols/8; // 8
        
        for(int row_inicio = 0 ; row_inicio < num_bloques_row -1  ; row_inicio ++){
            for( int col_inicio = 0 ; col_inicio < num_bloques_col-1 ; col_inicio++){
                results = getBloque(img, row_inicio*8, col_inicio*8);
                aux = getHistograma(results);
                for(float n : aux) histogramas.add(n);
            }
        }
        descriptor.fromList(histogramas);
    }
    
    private String getLBPCoeficient(double value1,double value2){
        return (value1 < value2 ? "0" : "1");
    }
    
    private ArrayList<Integer> getBloque(Mat img, int row_inicio, int col_inicio){
        ArrayList<Integer> results = new ArrayList<Integer>();
        String binary;
        
        for( int i = row_inicio ; i < row_inicio + 16 && i < img.rows(); i++){
            for(int j = col_inicio ; j < col_inicio + 16 && j < img.cols() ; j++){
                binary = "";
                if ( i > 0 ){
                    if(j>0) binary += getLBPCoeficient(img.get(i-1,j-1)[0] , img.get(i,j)[0]);
                    binary += getLBPCoeficient(img.get(i-1,j)[0] , img.get(i,j)[0]);
                    if(j<img.cols()-1)binary += getLBPCoeficient(img.get(i-1,j+1)[0] , img.get(i,j)[0]);
                }
                
                if(j<img.cols()-1){
                    binary += getLBPCoeficient(img.get(i,j+1)[0] , img.get(i,j)[0]);
                    if( i < img.rows()-1) binary += getLBPCoeficient(img.get(i+1,j+1)[0] , img.get(i,j)[0]);
                }
                
                
                if( i < img.rows()-1) binary += getLBPCoeficient(img.get(i+1,j)[0] , img.get(i,j)[0]);
                if(j>0) {
                    if( i < img.rows()-1) binary += getLBPCoeficient(img.get(i+1,j-1)[0] , img.get(i,j)[0]);
                    binary += getLBPCoeficient(img.get(i,j-1)[0] , img.get(i,j)[0]);
                }
                
                if(binary!="") results.add(Integer.parseInt(binary,2));
            }
        }
        return results;
    }
    
    /**
     * Lee las imágenes de entrenamiento (positivas y negativas) y calcula sus
     * descriptores para el entrenamiento.
     *
     * @param trainingData matriz donde almacenará los datos de entrenamiento
     * @param classes matriz donde almacenará las clases asociaas a los datos de
     * entrenamiento
     * @param type string que indica si se recogen los datos de train o de test
     * @param descriptor_nombre string que indica si se utilizará HoG o LBP
     */
    private void loadData(Mat trainingData, Mat classes,String type) {
        String PATH_POSITIVE_TRAIN = "data/"+type+"/pedestrians/";
        String PATH_NEGATIVE_TRAIN = "data/"+type+"/background/";
        File listFiles[];
        
        //Casos positivos
        listFiles = new File(PATH_POSITIVE_TRAIN).listFiles();
        for (File file : listFiles) {
            Mat img = Imgcodecs.imread(PATH_POSITIVE_TRAIN + file.getName(), Imgcodecs.CV_LOAD_IMAGE_COLOR);
            //HOG
            MatOfFloat descriptor = new MatOfFloat();
            if (this.descriptorName == "HoG"){
                HOGDescriptor hog = new HOGDescriptor();
                hog.compute(img, descriptor);
            }
            else if(this.descriptorName == "LBP") LBP(img,descriptor);
            else {
                /*
                HOGDescriptor hog = new HOGDescriptor();
                hog.compute(img, descriptor);
                MatOfFloat descriptor_lbp = new MatOfFloat();
                LBP(img,descriptor_lbp);
                descriptor.push_back(descriptor_lbp);
                */
                
                MatOfFloat descriptor_hog = new MatOfFloat();
                HOGDescriptor hog = new HOGDescriptor();
                hog.compute(img, descriptor_hog);
                MatOfFloat descriptor_lbp = new MatOfFloat();
                LBP(img,descriptor_lbp);
                Core.hconcat(Arrays.asList(descriptor_lbp.reshape(1,1), descriptor_hog.reshape(1,1)), descriptor);
            }
            
            //System.out.println(this.descriptorName);
            //System.out.println(descriptor.size());
            //Incorporamos el descriptor a la matriz de entrenamiento y la 
            //etiqueta '1' a la matriz de etiquetas asociada
            trainingData.push_back(descriptor.reshape(1, 1)); //Vector fila; flotante
            classes.push_back(Mat.ones(new Size(1, 1), CvType.CV_32SC1)); // Vector de un elemento; entero
        }
        
        String text = editorOutput.getText() + "Leidas " + listFiles.length + " imágenes de entrenamiento positivas";
        editorOutput.setText(text);
        //Casos negativos
        listFiles = new File(PATH_NEGATIVE_TRAIN).listFiles();
        for (File file : listFiles) {
            Mat img = Imgcodecs.imread(PATH_NEGATIVE_TRAIN + file.getName(), Imgcodecs.CV_LOAD_IMAGE_COLOR);
            MatOfFloat descriptor = new MatOfFloat();
            if (this.descriptorName == "HoG"){
                HOGDescriptor hog = new HOGDescriptor();
                hog.compute(img, descriptor);
            }
            else if(this.descriptorName == "LBP") LBP(img,descriptor);
            else {
                HOGDescriptor hog = new HOGDescriptor();
                hog.compute(img, descriptor);
                MatOfFloat descriptor_aux = new MatOfFloat();
                LBP(img,descriptor_aux);
                descriptor.push_back(descriptor_aux);
            }

            //Incorporamos el descriptor a la matriz de entrenamiento y la 
            //etiqueta '0' a la matriz de etiquetas asociada
            trainingData.push_back(descriptor.reshape(1, 1));
            classes.push_back(Mat.zeros(new Size(1, 1), CvType.CV_32SC1));
        }
        
        text = editorOutput.getText() + "\nLeidas " + listFiles.length + " imágenes de entrenamiento negativas";
        editorOutput.setText(text);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar barraArchivo;
    private javax.swing.JPanel barraEstado;
    private javax.swing.JToolBar barraImagen;
    private javax.swing.JButton botonAbrir;
    private javax.swing.JButton botonBondad;
    private javax.swing.JButton botonCanny;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton botonHarris;
    private javax.swing.JButton botonParametros;
    private javax.swing.JButton botonTest;
    private javax.swing.JButton botonTrain;
    private javax.swing.JMenuItem clear;
    private javax.swing.JMenuItem closeAll;
    private javax.swing.JRadioButtonMenuItem colorDominante;
    private javax.swing.JRadioButtonMenuItem colorDominanteDB;
    private javax.swing.JRadioButtonMenuItem colorEscalable;
    private javax.swing.JRadioButtonMenuItem colorEscalableDB;
    private javax.swing.JRadioButtonMenuItem colorEstructurado;
    private javax.swing.JRadioButtonMenuItem colorEstructuradoDB;
    private javax.swing.JRadioButtonMenuItem colorMedio;
    private javax.swing.JRadioButtonMenuItem colorMedioDB;
    private javax.swing.JEditorPane editorOutput;
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuItem menuAbrir;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuGuardar;
    public javax.swing.JMenu menuVer;
    private javax.swing.JMenu menuZoom;
    private javax.swing.JMenuItem menuZoomIn;
    private javax.swing.JMenuItem menuZoomOut;
    private javax.swing.JPanel panelBarraHerramientas;
    private javax.swing.JPanel panelOutput;
    private javax.swing.JTabbedPane panelTabuladoInfo;
    private javax.swing.JPopupMenu popupMenuGrid;
    private javax.swing.JPopupMenu popupMenuPanelOutput;
    private javax.swing.JPopupMenu popupMenuSeleccionDescriptores;
    private javax.swing.JPopupMenu popupMenuSeleccionDescriptoresDB;
    public javax.swing.JLabel posicionPixel;
    private javax.swing.JScrollPane scrollEditorOutput;
    private javax.swing.JPopupMenu.Separator separador1;
    private javax.swing.JPopupMenu.Separator separadorDescriptores;
    private javax.swing.JPopupMenu.Separator separadorDescriptoresDB;
    private javax.swing.JLabel showPanelInfo;
    public javax.swing.JSplitPane splitPanelCentral;
    private javax.swing.JRadioButtonMenuItem texturaEdge;
    private javax.swing.JRadioButtonMenuItem texturaEdgeDB;
    private javax.swing.JRadioButtonMenuItem texturaHomogeneidad;
    private javax.swing.JRadioButtonMenuItem texturaHomogeneidadDB;
    private javax.swing.JComboBox<String> tipoDescriptor;
    private javax.swing.JCheckBoxMenuItem verGrid;
    // End of variables declaration//GEN-END:variables

}
