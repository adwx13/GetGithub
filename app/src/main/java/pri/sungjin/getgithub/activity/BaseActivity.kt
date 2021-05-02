package pri.sungjin.getgithub.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.Disposable
import pri.sungjin.getgithub.util.RxEventBus

open class BaseActivity : AppCompatActivity() {

    private var disposables: Disposable? = null
    private var eventBusInterface: RxEventBus.EventBusInterface = object: RxEventBus.EventBusInterface { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        eventBusInterface = initDisposable()
        subscribeDisposable()
    }

    override fun onDestroy() {
        disposeDisposable()
        super.onDestroy()
    }

    open fun initDisposable(): RxEventBus.EventBusInterface {
        return object: RxEventBus.EventBusInterface {}
    }

    fun subscribeDisposable() {
        disposables = RxEventBus.getInstance().getBus().subscribe {
            eventBusInterface.onAlive(it)
        }
    }

    fun disposeDisposable() {
        if (disposables != null && !disposables!!.isDisposed) {
            disposables!!.dispose()
        }
    }
}