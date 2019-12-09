package com.xiekang.exercise.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServiceSocketDemo {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            ServerSocket server = new ServerSocket(8080);
            socket = server.accept();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));//输入流
            PrintWriter pw = new PrintWriter(socket.getOutputStream());//输出流


            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//终端输入流
            String line = null;
            line = br.readLine();//获取到终端输入的字符串
            while (!line.equals("bye")){
                pw.print(line);
                pw.flush();
                System.out.println("client:"+bufferedReader.readLine());
                System.out.println("server:"+line);
                line = br.readLine();
            }
            bufferedReader.close();
            pw.close();
            socket.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
