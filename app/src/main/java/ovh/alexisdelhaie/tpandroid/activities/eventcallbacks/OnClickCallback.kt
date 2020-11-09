package ovh.alexisdelhaie.tpandroid.activities.eventcallbacks

import ovh.alexisdelhaie.tpandroid.pojos.SimpleObject

interface OnClickCallback<T> {

    fun onItemClick(obj: T)

}