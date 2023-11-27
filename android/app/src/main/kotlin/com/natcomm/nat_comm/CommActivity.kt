package com.natcomm.nat_comm

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.Result

const val CHANNEL = "my_channel";

class CommActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comm)

        val flutterEngine: FlutterEngine = FlutterEngineCache.getInstance().get(ENGINE_ID)!!
        val channel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)

        findViewById<Button>(R.id.button).setOnClickListener {
            val number1 =
                findViewById<EditText>(R.id.number1).text.toString().ifEmpty { "0" }.toInt()
            val number2 =
                findViewById<EditText>(R.id.number2).text.toString().ifEmpty { "0" }.toInt()
            channel.invokeMethod(
                "sum",
                mapOf("arg1" to number1, "arg2" to number2),
                object : Result {
                    override fun success(result: Any?) {
                        findViewById<TextView>(R.id.result).text = "sum = $result"
                    }

                    override fun error(
                        errorCode: String,
                        errorMessage: String?,
                        errorDetails: Any?
                    ) {
                        findViewById<TextView>(R.id.result).text = "error"
                    }

                    override fun notImplemented() {
                        findViewById<TextView>(R.id.result).text = "notImplemented"
                    }
                })
        }
    }
}