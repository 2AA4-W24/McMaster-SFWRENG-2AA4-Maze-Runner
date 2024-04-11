package ca.mcmaster.se2aa4.mazerunner;

public interface PathOutput {
    static String factorPath(String path) {
        String new_path = "";
        Integer count;
        Character move_type;

        if (!(path.equals(""))) {
            for (int i = 0; i < path.length() - 1; i++) {
                count = 0;
                move_type = path.charAt(i);
                if (move_type != ' ') {
                    count += 1;
                    for (int j = i + 1; path.charAt(j) == move_type; j++) {
                        count += 1;
                    }
                }
                if (count > 1) {
                    new_path += count + Character.toString(move_type) + " ";
                }
                else {
                    new_path += move_type + " ";
                }
                i += count;
            }
        }
        return new_path;
    }
}
