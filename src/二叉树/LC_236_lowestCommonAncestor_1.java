package 二叉树;

import java.util.ArrayList;
import java.util.Arrays;

public class LC_236_lowestCommonAncestor_1 {
    ArrayList<TreeNode> list1;
    ArrayList<TreeNode> list2;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null)
            return null;

        dfs(root,new ArrayList<>(),p,q);
        TreeNode node=null;
        if(list1.size()> list2.size()){
            for (int i = 0; i < list2.size(); i++) {
                if(list1.get(i)== list2.get(i)){
                    node=list1.get(i);
                }
            }
        }else{
            for (int i = 0; i < list1.size(); i++) {
                if(list1.get(i)== list2.get(i)){
                    node=list1.get(i);
                }
            }
        }
        return node;
    }
    private void dfs(TreeNode root, ArrayList<TreeNode> list, TreeNode p, TreeNode q){
        if(root==null)
            return;
        list.add(root);
        if(root==p)
            list1= new ArrayList<>(list);
        if(root==q)
            list2= new ArrayList<>(list);
        dfs(root.left,list,p,q);
        dfs(root.right,list,p,q);
        list.remove(root);
    }
}
