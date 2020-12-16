package com.example.kedi.ui.proposals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.baoyachi.stepview.HorizontalStepView
import com.baoyachi.stepview.VerticalStepView
import com.baoyachi.stepview.bean.StepBean
import com.example.kedi.R


class ProposalsFragment : Fragment() {

    private lateinit var notificationsViewModel: ProposalsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(ProposalsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_create, container, false)
        //val textView: TextView = root.findViewById(R.id.text_notifications)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            //textView.text = it
        })
        return root
    }

    override fun onActivityCreated(state: Bundle?) {
        super.onActivityCreated(state)


        val setpview5 = requireView().findViewById<View>(R.id.horizontalStepView) as HorizontalStepView
        val stepsBeanList: MutableList<StepBean> = ArrayList()
        val stepBean0 = StepBean("Tipo de Servicio", -1)
        val stepBean1 = StepBean("Información", -1)
        val stepBean2 = StepBean("Publica", -1)
        stepsBeanList.add(stepBean0)
        stepsBeanList.add(stepBean1)
        stepsBeanList.add(stepBean2)


        setpview5
            .setStepViewTexts(stepsBeanList) //总步骤
            .setTextSize(12) //set textSize
            .setStepsViewIndicatorCompletedLineColor(
                ContextCompat.getColor(
                    requireActivity(),
                    android.R.color.black
                )
            ) //设置StepsViewIndicator完成线的颜色
            .setStepsViewIndicatorUnCompletedLineColor(
                ContextCompat.getColor(
                    requireActivity(),
                    android.R.color.black
                )
            ) //设置StepsViewIndicator未完成线的颜色
            .setStepViewComplectedTextColor(
                ContextCompat.getColor(
                    requireActivity(),
                    android.R.color.black
                )
            ) //设置StepsView text完成线的颜色
            .setStepViewUnComplectedTextColor(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.create
                )
            ) //设置StepsView text未完成线的颜色
            .setStepsViewIndicatorCompleteIcon(
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.ic_completed
                )
            ) //设置StepsViewIndicator CompleteIcon
            .setStepsViewIndicatorDefaultIcon(
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.ic_uncompleted
                )
            ) //设置StepsViewIndicator DefaultIcon
            .setStepsViewIndicatorAttentionIcon(
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.ic_baseline_add_circle_outline_24_orange
                )
            ) //设置StepsViewIndicator AttentionIcon


    }
}