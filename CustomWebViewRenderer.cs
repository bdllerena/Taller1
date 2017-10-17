using Android.Content;
using Android.Content.Res;
using Android.Widget;
using EspePocket;
using EspePocket.Droid;
using System;
using System.IO;
using System.Net;
using Xamarin.Forms;
using Xamarin.Forms.Platform.Android;


[assembly: ExportRenderer(typeof(CustomWebView), typeof(CustomWebViewRenderer))]
namespace EspePocket.Droid
{
    public class CustomWebViewRenderer : Xamarin.Forms.Platform.Android.WebViewRenderer
    {

       protected override void OnElementChanged(ElementChangedEventArgs<WebView> e)
        {
            base.OnElementChanged(e);
            if (Control != null)
            {
                Control.Settings.BuiltInZoomControls = true;
                Control.Settings.DisplayZoomControls = true;
            }
            if (e.NewElement != null)
            {

                if (Android.OS.Build.VERSION.SdkInt >= Android.OS.BuildVersionCodes.Kitkat)
                {
                    var customWebView = Element as CustomWebView;
                    Control.Settings.JavaScriptEnabled = true;
                    Control.Settings.AllowUniversalAccessFromFileURLs = true;
                    Control.Settings.AllowFileAccessFromFileURLs = true;
                    // Control.LoadUrl(string.Format("file:///android_asset/pdfjs/web/viewer.html?file={0}", string.Format("file:///android_asset/Content/{0}", WebUtility.UrlEncode(customWebView.Uri))));
                    // Control.LoadUrl("https://docs.google.com/viewer?url=file:///android_asset/Content/Aereo");
                  

                }
                else
                {
                   /* var customWebView = Element as CustomWebView;
                    Control.Settings.JavaScriptEnabled = true;
                    Control.Settings.AllowUniversalAccessFromFileURLs = true;
                    Control.Settings.AllowFileAccessFromFileURLs = true;
                    Control.LoadUrl("https://docs.google.com/viewer?url=file:///android_asset/Content/Aereo");
                    //String path = Uri.parse("android_asset/Content/Aereo.pdf").toString();
                    //Control.LoadUrl("https://docs.google.com/viewer?url="+path);
                    // var documentsPath = Environment.GetFolderPath(Environment.SpecialFolder.Personal);
                    //var filePath = Path.Combine(documentsPath, customWebView.Uri);
                    //var filePath = string.Format("android_asset/Content/{0}", WebUtility.UrlEncode(customWebView.Uri));
                    // string filePath = "android_asset/Content/Aereo.pdf";
                    //OpenPdf(filePath);
                    //OpenFileByName(WebUtility.UrlEncode(customWebView.Uri));*/
                }
            }
        }

        public void OpenPdf(string filePath)
        {
            Android.Net.Uri uri = Android.Net.Uri.Parse("file:///" + filePath);
            Intent intent = new Intent(Intent.ActionView);
            intent.SetDataAndType(uri, "application/pdf");
            intent.SetFlags(ActivityFlags.ClearWhenTaskReset | ActivityFlags.NewTask);

            try
            {
                Xamarin.Forms.Forms.Context.StartActivity(intent);
            }
            catch (Exception)
            {
                Toast.MakeText(Xamarin.Forms.Forms.Context, "No dispone de una aplicación para visualizar el PDF", ToastLength.Short).Show();
            }
        }
        public void OpenFileByName(string fileName)
        {
            try
            {
                var documentsPath = Environment.GetFolderPath(Environment.SpecialFolder.Personal);
                var filePath = Path.Combine(documentsPath, fileName);
                var bytes = File.ReadAllBytes(filePath);

                //Copy the private file's data to the EXTERNAL PUBLIC location
                string externalStorageState = global::Android.OS.Environment.ExternalStorageState;
                var externalPath = global::Android.OS.Environment.ExternalStorageDirectory.Path + "/" + global::Android.OS.Environment.DirectoryDownloads + "/" + fileName;
                File.WriteAllBytes(externalPath, bytes);

                Java.IO.File file = new Java.IO.File(externalPath);
                file.SetReadable(true);

                string application = "";
                string extension = Path.GetExtension(filePath);

                // get mimeTye
                switch (extension.ToLower())
                {
                    case ".txt":
                        application = "text/plain";
                        break;
                    case ".doc":
                    case ".docx":
                        application = "application/msword";
                        break;
                    case ".pdf":
                        application = "application/pdf";
                        break;
                    case ".xls":
                    case ".xlsx":
                        application = "application/vnd.ms-excel";
                        break;
                    case ".jpg":
                    case ".jpeg":
                    case ".png":
                        application = "image/jpeg";
                        break;
                    default:
                        application = "*/*";
                        break;
                }

                //Android.Net.Uri uri = Android.Net.Uri.Parse("file://" + filePath);
                Android.Net.Uri uri = Android.Net.Uri.FromFile(file);
                Intent intent = new Intent(Intent.ActionView);
                intent.SetDataAndType(uri, application);
                intent.SetFlags(ActivityFlags.ClearWhenTaskReset | ActivityFlags.NewTask);

                Forms.Context.StartActivity(intent);
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }
        }
       }
    }