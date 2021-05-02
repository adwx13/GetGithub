package pri.sungjin.getgithub.util

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RxEventBus {
    companion object {
        @Volatile
        private var sInstance: RxEventBus? = null

        fun getInstance(): RxEventBus {
            synchronized(RxEventBus::class.java) {
                if (sInstance == null) {
                    sInstance = RxEventBus()
                }
            }
            return sInstance!!
        }
    }

    private var mSubject: PublishSubject<EventBusObject> = PublishSubject.create()

    fun sendBus(busObject: EventBusObject) {
        mSubject.onNext(busObject)
    }

    fun sendBus(tag: BUS_TAG, `object`: Any? = null) {
        val busObject = EventBusObject(tag, `object`)
        sendBus(busObject)
    }

    fun getBus(): Observable<EventBusObject> {
        return mSubject
    }

    class EventBusObject(var tag: BUS_TAG, var `object`: Any? = null)

    interface EventBusInterface {
        fun onAlive(eventBusObject: EventBusObject) { }
    }

    enum class BUS_TAG {
        RESTORE_VIEWMODEL
    }
}