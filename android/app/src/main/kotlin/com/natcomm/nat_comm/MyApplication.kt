package com.natcomm.nat_comm

import androidx.multidex.MultiDexApplication
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor.DartEntrypoint

const val ENGINE_ID = "my_flutter_engine"

class MyApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        // Instantiate a FlutterEngine.
        val flutterEngine = FlutterEngine(this)

        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartEntrypoint.createDefault()
        )

        // Cache the FlutterEngine to be used by FlutterActivity.
        FlutterEngineCache
            .getInstance()
            .put(ENGINE_ID, flutterEngine)
    }
}