package cn.alauncher.pdfdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private PDFView view_pdfview;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                Bundle bundle = msg.getData();
                String path = bundle.getString("path");
                if (!path.equals("") && path.endsWith(".pdf")) {
                    /**/
                    File file = new File(path);
                    view_pdfview.fromFile(file)//加载路径
                            .defaultPage(0)//默认打开的页面
                            .swipeHorizontal(true)//是否横向滑动
                            .enableAnnotationRendering(true)
                            .enableDoubletap(true)//双击放大
                            .load();//加载
                    view_pdfview.setVisibility(View.VISIBLE);

                    // openPDFInNative(MainActivity.this, path);
                }
            }
        }
    };

    @Override
    public void onBackPressed() {
        if (view_pdfview.getVisibility() == View.VISIBLE) {
            view_pdfview.setVisibility(View.GONE);
            // createLayout.setVisibility(View.VISIBLE);
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view_pdfview = findViewById(R.id.view_pdfview);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    File file = new File(C01E04_Table.DEST);
                    if (file.exists()) file.delete();
                    file.getParentFile().mkdirs();
                    C01E04_Table.createNTTable();
                    Message message = new Message();
                    message.what = 0;
                    Bundle bundle = new Bundle();
                    bundle.putString("path", file.getAbsolutePath());
                    message.setData(bundle);
                    mHandler.sendMessageDelayed(message, 1500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void openPDFInNative(Context context, String FILE_NAME) {
        File file = new File(FILE_NAME);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(file);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, "application/pdf");
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.w("URLSpan", "Activity was not found for intent, " + intent.toString());
        }
    }
}
