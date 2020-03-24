package pokemontcg.libraries.ui_components

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

open class SingleLiveEvent<T> : MutableLiveData<T>() {

    private val mPending: AtomicBoolean = AtomicBoolean(false)

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            //"Multiple observers registered but only one will be notified of changes."
        }

        //Observe the internal MutableLiveData
        super.observe(owner, Observer {
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(it)
            }
        })
    }

    fun observe(owner: LifecycleOwner, work: () -> Unit) {
        observe(owner, Observer { work() })
    }

    @MainThread
    override fun setValue(t: T?) {
        mPending.set(true)
        super.setValue(t)
    }

    /**
     * Used for cases where T is Void?, to make calls cleaner
     */
    @MainThread
    fun call() {
        value = null
    }

    //pra manter a mesma estrutura do call
    @MainThread
    fun call(t: T?) {
        value = t
    }
}