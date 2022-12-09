export interface VideoCompressionPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  compressVideo(options: VideoCompressionOption): Promise<{ value: string }>;
}

export interface VideoCompressionOption {
  filePath: string;
}
