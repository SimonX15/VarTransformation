package simon.com

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys

class TransformationAction : AnAction() {

    override fun actionPerformed(anActionEvent: AnActionEvent) {
        val project = anActionEvent.getData(PlatformDataKeys.PROJECT)
        val editor = anActionEvent.getData(PlatformDataKeys.EDITOR)
        //        editor.getSelectionModel().getSelectedText()

        //对选中的变量进行处理
        if (editor != null && project != null) {
            //选择的内容
            val selectedText = editor.selectionModel.selectedText
            if (selectedText.isNullOrEmpty()) {
                return
            }

            //通过下划线分割，并且下划线不是第一个字符
            if (selectedText!!.contains("_")) {
                val splitWords = selectedText.trim().split("_")
                //驼峰
                var humpWords = ""
                for (index in 0 until splitWords.size) {
                    val word = splitWords[index]
                    humpWords += if (index == 0) {
                        word
                    } else {
                        when (word.length) {
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

                //大写
                val uppercaseWords = selectedText.trim().toUpperCase()

                //小写
                val lowercaseWords = selectedText.trim().toLowerCase()

                val wordList: ArrayList<String> = ArrayList()
                wordList.add(humpWords)
                wordList.add(uppercaseWords)
                wordList.add(lowercaseWords)

                /*val chooseDialog = ChooseDialog()
//                val chooseDialog = ChooseDialog(wordList)
                chooseDialog.run {
                    setSize(400, 400)
                    val component = editor.component
                    //FIXME，显示的位置还需要调整
                    setLocationRelativeTo(component)
//                    location = editor.offsetToXY(10)
                    isVisible = true
                }*/
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
}
