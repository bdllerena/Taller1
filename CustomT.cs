using EspePocket.Droid;
using Xamarin.Forms;
using Xamarin.Forms.Platform.Android;


[assembly: ExportRenderer(typeof(WebView), typeof(CustomT))]
namespace EspePocket.Droid
{
    public class CustomT : Xamarin.Forms.Platform.Android.WebViewRenderer
    {

        protected override void OnElementChanged(ElementChangedEventArgs<WebView> e)
        {
            base.OnElementChanged(e);

            if (Control != null)
            {
                Control.Settings.BuiltInZoomControls = true;
                Control.Settings.DisplayZoomControls = false;
                Control.Settings.JavaScriptEnabled = true;
                Control.Settings.UseWideViewPort = true;
                Control.SetInitialScale(1);

            }
        }



    }
}