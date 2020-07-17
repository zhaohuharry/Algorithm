public class main {    
        public static int KMP(String src, String target) {
            //return the first index if target is contained in the source and -1 otherwise
            int[]res=calculate(target);
            int i=0,j=0;
            while (i<src.length() && j < target.length()){
                if(src.charAt(i)== target.charAt(j)){
                    i++;
                    j++;
                }else{
                    while (j>0 && src.charAt(i)!=target.charAt(j)){
                        j=res[j-1];
                    }
                    if(src.charAt(i)==target.charAt(j)) j++;
                    i++;
                }
            }
            if(j==target.length()) return i-target.length();
            else return -1;
        }
    
        public static int[] calculate(String s) {
            int[] result = new int[s.length()];
            int j = 0;
            for (int i = 1; i < s.length(); ++i) {
                if (s.charAt(i) == s.charAt(j)) {
                    j++;
                    result[i] = j;
                } else {
                    while (j > 0 && s.charAt(i) != s.charAt(j)) {
                        j = result[j - 1];
                    }
                    if (s.charAt(i) == s.charAt(j)) {
                        j++;
                        result[i] = j;
                    } else {
                        result[i] = j;
                    }
                }
            }
    
            return result;
        }
    
    
        public static void main(String[] args) {
            String s = "aabaabaaa";
            int[] result = calculate(s);
            System.out.println(KMP("abxabcabcaby","abcaby"));
        }
    
    }
    