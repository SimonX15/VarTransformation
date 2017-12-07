package simon.com

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiUtilBase

class TransformationAction : AnAction() {

    override fun actionPerformed(anActionEvent: AnActionEvent) {
        //当前的项目和文件信息
        val project = anActionEvent.getData(PlatformDataKeys.PROJECT)
        val editor = anActionEvent.getData(PlatformDataKeys.EDITOR)
        /*PsiFile currentEditorFile = PsiUtilBase.getPsiFileInEditor(editor, project);
        String currentEditorFileName = currentEditorFile.getName();*/

        //当前选中的内容
        val selectedText = editor!!.selectionModel.selectedText

        /*
        //显示一个对话框
        Messages.showMessageDialog("Hello World !", "Information", Messages.getInformationIcon());*/
    }
}
