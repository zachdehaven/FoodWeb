package group2.foodWeb.user;

public class User
{
    static final int maxUsers = 16;
    static User[] userHashMap =new User[User.maxUsers];
    String username, password, email, bio;
    int userID;


    boolean registerUser(String email, String username, String password)
    {
        int index;

        for(index=0;index<maxUsers;index++)
        {
            if(userHashMap[index].username.compareTo(username)==0) {
                return false;
            }

            if(userHashMap[index].email.compareTo(email)==0) {
                return false;
            }
        }
        this.username=username;
        this.email=email;
        this.password=password;
        this.userID=generateHashUserID(this);
        userHashMap[this.userID]=this;
        return true;
    }

    void editBio(String bio)
    {
        this.bio=bio;
    }

    boolean changePassword(String oldPassword, String newPassword )
    {
        if(oldPassword.compareTo(this.password)==0)
        {
            this.password = newPassword;
            return true;
        }
        return false;
    }

    boolean changeUsername(String newUsername)
    {
        int index;
        for(index=0;index<maxUsers; index++)
        {
            if(userHashMap[index].username.compareTo(username)==0)
            {
                return false;
            }
        }
        this.username=newUsername;
        return true;
    }

    int generateHashUserID(User newUser)
    {

        int charIndex, hashIndex = 0;

        // sum ASCII character values - Josh Bloch Recipe for hash codes
        for ( charIndex = 0; charIndex < newUser.username.length(); charIndex++ )
        {
            hashIndex += (int)newUser.username.charAt( charIndex );
        }

        // convert to usable index
        hashIndex %= maxUsers;

        return hashIndex;

    }
}
