/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.User;

/**
 *
 * @author luhtalam
 */
public class FileUserDao implements UserDao {

    private String filename;

    public FileUserDao(String filename) {
        this.filename = filename;
    }

    @Override
    public List<User> listAll() {
        try {
            Scanner scan = new Scanner(new File(filename));
            ArrayList<User> list = new ArrayList<>();
            String s1,s2;
            while(scan.hasNextLine()) {
                s1 = scan.nextLine();
                s2 = scan.nextLine();
                list.add(new User(s1,s2));
            }
            return list;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public User findByName(String name) {
        try {
            Scanner scan = new Scanner(new File(filename));
            while(scan.hasNextLine()) {
                String s = scan.nextLine();
                if(s.equals(name)) {
                    String s1 = scan.nextLine();
                    return new User(s,s1);
                }
            }
            return null;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void add(User user) {
        try {
            FileWriter writer = new FileWriter(filename, true);
            writer.write(user.getUsername()+"\n");
            writer.write(user.getPassword()+"\n");
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
