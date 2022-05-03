import { Box, Button, Stack } from "@mui/material";
import { Completed } from "components/Completed";
import { ApiMethod } from "components/Components.styled";
import styles from "./styles";

interface PropsType {
  API: any;
  handleShow: () => void;
}

export const DropdownButton: React.FC<PropsType> = (props: PropsType) => {
  const API = props.API;
  const handleShow = props.handleShow;

  return (
    <>
      <Button sx={styles.apiHeader} onClick={handleShow}>
        <Stack
          direction="row"
          justifyContent="space-between"
          sx={{ width: "100%" }}
        >
          <Stack direction="row" alignItems="center" spacing={2}>
            <Completed completed={API.completed} />
            <Box style={styles.apiDetail}>{API.detail}</Box>
          </Stack>
          <Stack direction="row" alignItems="center" spacing={2}>
            <Box sx={styles.apiUri}>{API.uri}</Box>
            <ApiMethod method={API.method}>{API.method}</ApiMethod>
          </Stack>
        </Stack>
      </Button>
    </>
  );
};
