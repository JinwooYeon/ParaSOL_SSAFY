import { Box, Stack, Button } from "@mui/material";
import styles from "./styles";

interface PropsType {
  responseData: any;
  setResponseData: (a: any) => void;
}

export const Response: React.FC<PropsType> = (props: PropsType) => {
  const responseStatus = props.responseData.status;
  const output = props.responseData.output;
  const setResponseData = props.setResponseData;
  return (
    <>
      <Stack justifyContent="center" alignItems="center">
        <Stack
          direction="row"
          justifyContent="space-between"
          alignItems="center"
          style={styles.outputHeader}
        >
          <Box sx={{ fontWeight: "bold", fontSize: 20 }}>출력값</Box>
          <Stack direction="row" spacing={2}>
            <Box>응답코드</Box>
            {responseStatus === 201 || responseStatus === 200 ? (
              <Box style={styles.responseStatus.success}>
                {responseStatus && responseStatus}
              </Box>
            ) : (
              <Box style={styles.responseStatus.failed}>
                {responseStatus && responseStatus}
              </Box>
            )}
          </Stack>
        </Stack>
        <Box style={styles.outputStyle}>
          <Box sx={{ wordWrap: "break-word" }}>{output}</Box>
        </Box>
      </Stack>
      <Button
        variant="outlined"
        color="error"
        onClick={() =>
          setResponseData({
            status: "",
            ouput: "",
          })
        }
      >
        출력값 초기화
      </Button>
    </>
  );
};
