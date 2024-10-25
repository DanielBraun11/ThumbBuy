import javax.swing.*;
import java.awt.*;
import java.time.YearMonth;
import java.util.Calendar;

public class InterfazPago extends Herramientas {
    public InterfazPago() {
        // CREACION DE LA VENTANA
        JFrame ventanaPago = crearVentana();

        // CREACION PANEL DE PAGO
        JPanel panelPago = crearPanel();
        panelPago.setLayout(null); // Desactivar el layout para usar posiciones absolutas
        panelPago.setBackground(Color.pink);
        ventanaPago.add(panelPago);

        // TEXTO PAYMENT
        JLabel payment = new JLabel("PAYMENT");
        payment.setBounds(120, 20, 150, 50);
        payment.setFont(new Font("Arial", Font.BOLD, 24));
        panelPago.add(payment);

        // IMAGEN TARJETA CREDITO
        JLabel targetaCredito = crearEtiqueta();
        targetaCredito.setBounds(23, 70, 300, 150);

        ImageIcon iconoTarjeta = new ImageIcon("Imagenes/lvl.png");
        Image imagenTarjeta = iconoTarjeta.getImage();
        Image imagenTarjetaEscalada = imagenTarjeta.getScaledInstance(300, 150, Image.SCALE_SMOOTH);
        targetaCredito.setIcon(new ImageIcon(imagenTarjetaEscalada));

        panelPago.add(targetaCredito);

        // NOMBRE DEL TITULAR
        JLabel nombreTitularEtiqueta = crearEtiqueta();
        nombreTitularEtiqueta.setText("NOMBRE DEL TITULAR: ");
        nombreTitularEtiqueta.setBounds(40, 250, 200, 25);
        panelPago.add(nombreTitularEtiqueta);

        JTextArea nombreTitular = crearAreaTexto();
        nombreTitular.setBounds(40, 275, 280, 25);
        panelPago.add(nombreTitular);

        // NUMERO DE TARJETA
        JLabel numeroTarjeta = crearEtiqueta();
        numeroTarjeta.setText("NÚMERO DE LA TARJETA: ");
        numeroTarjeta.setBounds(40, 310, 200, 25);
        panelPago.add(numeroTarjeta);

        JTextArea numeroDeTarjeta = crearAreaTexto();
        numeroDeTarjeta.setBounds(40, 335, 280, 25);
        panelPago.add(numeroDeTarjeta);

        // FECHA DE CADUCIDAD
        JLabel fechaCaducidad = crearEtiqueta();
        fechaCaducidad.setText("FECHA DE CADUCIDAD (MM/AA): ");
        fechaCaducidad.setBounds(40, 370, 200, 25);
        panelPago.add(fechaCaducidad);

        // Áreas de texto para mes y año
        JTextArea mesCaducidad = crearAreaTexto();
        mesCaducidad.setBounds(40, 395, 50, 25); // Campo para el mes (MM)
        panelPago.add(mesCaducidad);

        // Etiqueta de barra "/"
        JLabel barraSeparadora = new JLabel("/");
        barraSeparadora.setBounds(95, 395, 10, 25);
        panelPago.add(barraSeparadora);

        // Campo para el año (AA)
        JTextArea anioCaducidad = crearAreaTexto();
        anioCaducidad.setBounds(110, 395, 50, 25); // Campo para el año (AA)
        panelPago.add(anioCaducidad);

        // CODIGO CVC
        JLabel codigoCVC = crearEtiqueta();
        codigoCVC.setText("CVC: ");
        codigoCVC.setBounds(265, 370, 50, 25);
        panelPago.add(codigoCVC);

        JTextArea codigo = crearAreaTexto();
        codigo.setBounds(265, 395, 50, 25);
        panelPago.add(codigo);

        // BOTÓN PAGO
        JButton botonPago = new JButton("Pagar");
        botonPago.setBounds(40, 450, 120, 30);
        panelPago.add(botonPago);

        botonPago.addActionListener(e -> {
            String nombre = nombreTitular.getText();
            String numeroCard = numeroDeTarjeta.getText();
            String mes = mesCaducidad.getText();
            String anio = anioCaducidad.getText();
            String cvc = codigo.getText();

            // Validaciones
            if (nombre.isEmpty() || numeroCard.isEmpty() || mes.isEmpty() || anio.isEmpty() || cvc.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos.");
                return;
            }

            if (numeroCard.length() != 16 || !numeroCard.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "El número de la tarjeta debe tener 16 dígitos.");
                return;
            }

            if (cvc.length() != 3 || !cvc.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "El número CVC debe tener 3 dígitos.");
                return;
            }

            // Validar fecha de caducidad
            try {
                int mesInt = Integer.parseInt(mes);

                // Validación del mes antes de continuar
                if (mesInt < 1 || mesInt > 12) {
                    JOptionPane.showMessageDialog(null, "El mes debe estar entre 01 y 12.");
                    return;
                }

                int anioInt = Integer.parseInt(anio) + 2000; // Convertir a año completo (AA -> AAAA)
                YearMonth fechaTarjeta = YearMonth.of(anioInt, mesInt);
                YearMonth fechaActual = YearMonth.now();

                if (fechaTarjeta.isBefore(fechaActual)) {
                    JOptionPane.showMessageDialog(null, "La tarjeta ha expirado.");
                    return;
                }

                JOptionPane.showMessageDialog(null, "Pago realizado con éxito.");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Formato de fecha inválido.");
            }
        });


        // BOTÓN CANCELAR
        JButton botonCancelar = new JButton("Cancelar");
        botonCancelar.setBounds(200, 450, 120, 30);
        panelPago.add(botonCancelar);

        botonCancelar.addActionListener(e -> {
            ventanaPago.dispose();
            //new InterfazPrincipal("");
        });


        // Dar visibilidad a la ventana
        ventanaPago.setVisible(true);
    }
}
