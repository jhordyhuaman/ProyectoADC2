/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pe.edu.upeu.spring.interfaces.Operaciones;
import pe.edu.upeu.spring.model.mobiliario;
import pe.edu.upeu.spring.util.Conexion;

/**
 *
 * @author Jose Rayo
 */
public class mobiliarioDAO implements Operaciones<mobiliario>{
    private PreparedStatement ps;
    private ResultSet rs;
    private Connection conex;
    private final static String SQL_CREATE = "INSERT INTO mobiliario (idCategoria, idMobiliario2, "
            + "nombre_Mob, marca_Mob, serie_Mob, cantidad, estado, fechaReg_Mob, comentario) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE = "UPDATE usuario SET clave=? WHERE idusuario=?";
    private final static String SQL_DELETE = "DELETE FROM usuario WHERE idusuario=?";
    private final static String SQL_SEARCH = "SELECT *FROM usuario WHERE user=?";
    private final static String SQL_READALL = "SELECT  m.idMobiliario, m.nombre_Mob, m.marca_Mob, "
            + "m.serie_Mob, m.cantidad, m.estado, m.fechaReg_Mob, m.comentario, c.nombre_Categ "
            + "FROM mobiliario m , categoria c WHERE c.idCategoria = m.idCategoria ORDER BY m.idMobiliario";
    private final static String SQL_BUSCAR = "SELECT *FROM usuario WHERE idusuario=?";
    private final static String SQL_listar = "SELECT *FROM mobiliario";
    @Override
    public int create(mobiliario d) {
        int op = 0;
        try {
            conex = Conexion.getConexion();
            ps =conex.prepareStatement(SQL_CREATE);
            ps.setInt(1, d.getIdCategoria());
            ps.setInt(2, d.getIdMobiliario2());
            ps.setString(3, d.getNombre_Mob());
            ps.setString(4, d.getMarca_Mob());
            ps.setString(5, d.getSerie_Mob());
            ps.setString(6, d.getCantidad());
            ps.setString(7, d.getEstado());
            ps.setString(8, d.getFechaReg_Mob());
            ps.setString(9, d.getComentario());
            op = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: "+e);
        }
        return op;        
        
    }

    @Override
    public int update(mobiliario d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<mobiliario> readAll() {
          List<mobiliario> lista = new ArrayList<>();
        try {
            conex = Conexion.getConexion();
            ps = conex.prepareStatement(SQL_listar);
            rs = ps.executeQuery();
            while (rs.next()) {
                mobiliario x = new mobiliario();
                x.setIdMobiliario(rs.getInt("idMobiliario"));
                x.setNombre_Mob(rs.getString("nombre_Mob"));
                x.setMarca_Mob(rs.getString("marca_Mob"));
                x.setSerie_Mob(rs.getString("serie_Mob"));
                x.setCantidad(rs.getString("cantidad"));
                x.setEstado(rs.getString("estado"));
                x.setFechaReg_Mob(rs.getString("fechaReg_Mob"));
                x.setComentario(rs.getString("comentario"));

                lista.add(x);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return lista;
        
    }

    @Override
    public List<mobiliario> buscar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Map<String, Object>> listar() {
         Map<String, Object> m = new HashMap<>();
         ArrayList<Map<String, Object>> lista = new ArrayList<>();
                 try {
            conex = Conexion.getConexion();
            ps = conex.prepareStatement(SQL_READALL);
            rs = ps.executeQuery();
            while(rs.next()){
                m.put("idMobiliario",rs.getInt("idMobiliario"));
                m.put("nombre_Mob", rs.getString("nombre_Mob"));
                m.put("marca_Mob", rs.getString("marca_Mob"));
                m.put("serie_Mob", rs.getString("serie_Mob"));
                m.put("cantidad", rs.getString("cantidad"));
                m.put("estado",rs.getString("estado"));
                m.put("fechaReg_Mob", rs.getString("fechaReg_Mob"));   
                m.put("comentario", rs.getString("comentario"));
                m.put("nombre_Categ", rs.getString("nombre_Categ"));   
                lista.add(m);

            }
        } catch (Exception e) {
            System.out.println("Error: "+e);
        }
        return lista;
        
    }
    
}