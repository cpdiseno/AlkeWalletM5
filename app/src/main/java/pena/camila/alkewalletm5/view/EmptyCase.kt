package pena.camila.alkewalletm5.view

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pena.camila.alkewalletm5.databinding.FragmentEmptyCaseBinding

class EmptyCase() : Fragment(), Parcelable {

    private lateinit var binding: FragmentEmptyCaseBinding

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {

    // Inflate the layout for this fragment
    binding = FragmentEmptyCaseBinding.inflate(inflater, container, false)

    return binding.root
}

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EmptyCase> {
        override fun createFromParcel(parcel: Parcel): EmptyCase {
            return EmptyCase(parcel)
        }

        override fun newArray(size: Int): Array<EmptyCase?> {
            return arrayOfNulls(size)
        }
    }
}