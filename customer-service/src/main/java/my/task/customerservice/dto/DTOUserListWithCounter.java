package my.task.customerservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Data
@RequiredArgsConstructor
public class DTOUserListWithCounter {

        private List<DTOUsersSearching> usersSearchingList;

        private long usersCounter;

}
