package me.sweetll.tucao.business.drrr.adapter

import android.support.v4.content.ContextCompat
import android.widget.ImageView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import me.sweetll.tucao.R
import me.sweetll.tucao.business.drrr.model.MultipleItem
import me.sweetll.tucao.business.drrr.model.Post
import me.sweetll.tucao.business.drrr.model.Reply
import me.sweetll.tucao.util.RelativeDateFormat

class ReplyAdapter(data: MutableList<MultipleItem>): BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder>(data) {

    init {
        addItemType(MultipleItem.TYPE_POST, R.layout.item_post)
        addItemType(MultipleItem.TYPE_REPLY_NUM, R.layout.item_reply_num_divider)
        addItemType(MultipleItem.TYPE_REPLY, R.layout.item_reply)
    }

    override fun convert(helper: BaseViewHolder, item: MultipleItem) {
        when (helper.itemViewType) {
            MultipleItem.TYPE_POST -> convertPost(helper, item.post())
            MultipleItem.TYPE_REPLY_NUM -> convertReplyDivider(helper, item.replyNum())
            MultipleItem.TYPE_REPLY -> convertReply(helper, item.reply())
        }
    }

    private fun convertPost(helper: BaseViewHolder, item: Post) {
        helper.setText(R.id.text_time, RelativeDateFormat.format(item.createDt))
        helper.setText(R.id.text_content, item.content)
        helper.setText(R.id.text_reply_num, "${item.replyNum}")
        helper.setText(R.id.text_vote_num, "${item.voteNum}")
        if (item.vote) {
            helper.getView<ImageView>(R.id.img_thumb_up).setColorFilter(ContextCompat.getColor(mContext, R.color.pink_500))
        } else {
            helper.getView<ImageView>(R.id.img_thumb_up).setColorFilter(ContextCompat.getColor(mContext, R.color.grey_600))
        }
    }

    private fun convertReplyDivider(helper: BaseViewHolder, replyNum: Int) {
        helper.setText(R.id.text_reply_num_divider, "共${replyNum}条回复")
    }

    private fun convertReply(helper: BaseViewHolder, reply: Reply) {
        helper.setText(R.id.text_time, RelativeDateFormat.format(reply.createDt))
        helper.setText(R.id.text_content, reply.content)
    }

}