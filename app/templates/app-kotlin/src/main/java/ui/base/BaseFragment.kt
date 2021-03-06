package <%= appPackage %>.ui.base

import <%= appPackage %>.di.HasComponent

import android.content.Context
import android.os.Bundle
import android.support.annotation.CallSuper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.support.v4.app.Fragment

abstract class BaseFragment<out P : BasePresenter<*>> : Fragment() {

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResource(), container, false)
    }

    override fun onCreate(bundle: Bundle? ) {
        super.onCreate(bundle)
        inject()
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        getPresenter().destroy()
    }

    fun getBaseActivity() : BaseActivity<*> {
        return activity as BaseActivity<*>
    }

    @CallSuper
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    protected fun <C> getComponent(componentType: Class<C>): C {
        return componentType.cast((activity as HasComponent<*>).getComponent())
    }

    protected abstract fun inject()

    protected abstract fun getLayoutResource(): Int

    protected abstract fun getPresenter(): P

}
