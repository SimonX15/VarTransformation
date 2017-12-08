package simon.com

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import simon.com.util.AbstractPluginAction
import simon.com.util.PluginUtils
import javax.swing.Icon

class TransformationAction(name: String?, description: String?, icon: Icon?) : AbstractPluginAction(name, description, icon) {

    override fun actionPerformed(anActionEvent: AnActionEvent) {
        val editor = anActionEvent.getData(PlatformDataKeys.EDITOR)
        //        editor.getSelectionModel().getSelectedText()

        //对选中的变量进行处理
        if (editor != null) {
            //选择的内容
            val selectionModel = editor.selectionModel
            val oldText = selectionModel.selectedText
            if (oldText.isNullOrEmpty()) {
                return
            }

            val document = editor.document

            // keep start and end index
            val selectionStart = selectionModel.selectionStart
            val selectionEnd = selectionModel.selectionEnd

            val wordList = transferList(oldText)

            val chooseDialog = ChooseDialog(wordList)
            chooseDialog.run {
                setChooseListener {
                    if (it.isNullOrEmpty()) {
                        return@setChooseListener
                    }
                    // replace text in editor
                    PluginUtils.executeWriteAction(this@TransformationAction, anActionEvent, Runnable {
                        document.deleteString(selectionStart, selectionEnd)
                        document.insertString(selectionStart, it)
                        // neuen Text selektieren
                        selectionModel.setSelection(selectionStart, selectionStart + it.length)
                    })
                }
                setSize(360, 400)
                val component = editor.component
                //FIXME，显示的位置还需要调整
                setLocationRelativeTo(component)
//                    location = editor.offsetToXY(10)
                isVisible = true
            }

        }


        //当前的项目和文件信息
        //        Project project = anActionEvent.getData(PlatformDataKeys.PROJECT);
        //        Editor editor = anActionEvent.getData(PlatformDataKeys.EDITOR);
        //        PsiFile currentEditorFile = PsiUtilBase.getPsiFileInEditor(editor, project);
        //        String currentEditorFileName = currentEditorFile.getName();

        //显示一个对话框
        //        Messages.showMessageDialog("Hello World !", "Information", Messages.getInformationIcon());
    }

    /**
     * 转换后的列表
     */
    private fun transferList(oldText: String?): ArrayList<String> {
        //下划线的变量，作为转换的原始数据
        var underlineWords = ""
        //驼峰
        var humpWords = ""
        //驼峰+m
        var humpMWords = ""
        //大写
        var uppercaseWords = ""
        //小写
        var lowercaseWords = ""

        //先转换为带下划线的变量
        if (oldText!!.contains("_")) {
            underlineWords = oldText.trim()
        } else {
            //没有下划线的，类似anIntTestA
            oldText.trim().forEach {
                //加下划线，全大写
                underlineWords += if (it.isUpperCase()) {
                    "_${it.toLowerCase()}"
                } else {
                    it
                }
            }
        }

        //有下划线的变量，an_int_test_a，_int_test_a
        val splitWords = underlineWords.split("_")

        //驼峰
        for (index in 0 until splitWords.size) {
            val word = splitWords[index]
            humpWords += if (index == 0) {
                word
            } else {
                parseFirstUpper(word)
            }
        }

        //驼峰+m
        humpMWords += "m"
        splitWords.forEach {
            humpMWords += parseFirstUpper(it)
        }

        //大写
        uppercaseWords = underlineWords.toUpperCase()
        //小写
        lowercaseWords = underlineWords.toLowerCase()


        val wordList: ArrayList<String> = ArrayList()
        wordList.add(oldText.trim())
        wordList.add(underlineWords)
        wordList.add(humpWords)
        wordList.add(humpMWords)
        wordList.add(uppercaseWords)
        wordList.add(lowercaseWords)

        return wordList
                .filter { !it.isEmpty() }
                .distinctBy { it }
                as ArrayList<String>
    }

    /**
     * 把第一个字母转换成大写
     */
    private fun parseFirstUpper(word: String): String {

        return when (word.length) {
        //只有一个单词，直接转大写
            0 -> {
                ""
            }
        //只有一个单词，直接转大写
            1 -> {
                word.toUpperCase()
            }
        //超过一个，只转第一个为大写
            else -> {
                val first2Upper = word[0].toUpperCase()
                first2Upper + word.substring(1)
            }
        }
    }
}
