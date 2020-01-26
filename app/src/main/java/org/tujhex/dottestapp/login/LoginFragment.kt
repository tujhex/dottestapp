package org.tujhex.dottestapp.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.tujhex.dottestapp.R
import org.tujhex.dottestapp.core.HasDiComponent
import org.tujhex.dottestapp.databinding.FragmentLoginBinding
import org.tujhex.dottestapp.login.model.LoginProviderFactory
import org.tujhex.dottestapp.login.model.LoginViewModel
import org.tujhex.dottestapp.login.navigation.VkLoginRouter
import org.tujhex.dottestapp.main.MainComponent
import javax.inject.Inject

/**
 * @author tujhex
 * since 21.01.20
 */
class LoginFragment : Fragment() {

    @Inject
    lateinit var vkLoginRouter: VkLoginRouter

    @Inject
    lateinit var loginFactory: LoginProviderFactory

    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    @Suppress("UNCHECKED_CAST")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity is HasDiComponent<*>) {
            (activity as HasDiComponent<MainComponent>).getComponent().plus().inject(this)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            vkLoginRouter.attach(activity!!)
            binding.model = ViewModelProvider(activity!!, loginFactory)[LoginViewModel::class.java]
            binding.lifecycleOwner = this
        }
    }

    override fun onDetach() {
        super.onDetach()
        vkLoginRouter.detach()
    }
}